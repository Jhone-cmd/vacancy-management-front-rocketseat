package br.com.jhonecmd.vacancy_management_front.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jhonecmd.vacancy_management_front.modules.candidate.dto.CreateCandidateDTO;
import br.com.jhonecmd.vacancy_management_front.modules.candidate.services.ApplyJobService;
import br.com.jhonecmd.vacancy_management_front.modules.candidate.services.CandidateService;
import br.com.jhonecmd.vacancy_management_front.modules.candidate.services.CreateCandidateService;
import br.com.jhonecmd.vacancy_management_front.modules.candidate.services.ListJobsService;
import br.com.jhonecmd.vacancy_management_front.modules.candidate.services.ProfileCandidateService;
import br.com.jhonecmd.vacancy_management_front.utils.FormatErrorMessage;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @Autowired
    private ListJobsService listJobsService;

    @Autowired
    private ApplyJobService applyJobService;

    @Autowired
    private CreateCandidateService createCandidateService;

    @GetMapping("/login")
    public String LoginCandidate() {
        return "modules/candidate/login";
    }

    @GetMapping("/create")
    public String CreateCandidate(Model model) {
        model.addAttribute("candidate", new CreateCandidateDTO());
        return "modules/candidate/create";
    }

    @PostMapping("/create")
    public String save(CreateCandidateDTO candidateDTO, Model model) {

        try {
            createCandidateService.execute(candidateDTO);
        } catch (HttpClientErrorException ex) {
            model.addAttribute("error", FormatErrorMessage.formatErrorMessage(ex.getResponseBodyAsString()));
        }

        model.addAttribute("candidate", candidateDTO);
        return "modules/candidate/create";
    }

    @PostMapping("/signIn")
    public String singIn(RedirectAttributes redirectAttributes, HttpSession session, String email, String password) {
        try {

            var token = candidateService.login(email, password);
            var grants = token.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" +
                            role.toString().toUpperCase()))
                    .toList();

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, grants);

            auth.setDetails(token.getAccess_token());

            SecurityContextHolder.getContext().setAuthentication(auth);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            session.setAttribute("token", token);

            return "redirect:/candidate/profile";

        } catch (HttpClientErrorException ex) {

            redirectAttributes.addFlashAttribute("error", "Credenciais Inválidas");
            return "redirect:/candidate/login";
        }

    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String profile(Model model) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            var result = profileCandidateService.execute(authentication.getDetails().toString());

            model.addAttribute("candidate", result);

            return "modules/candidate/profile";
        } catch (HttpClientErrorException ex) {
            return "redirect:/candidate/login";
        }

    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String jobs(Model model, String filter) {

        try {

            var jobs = listJobsService.execute(getToken(), filter);
            model.addAttribute("jobs", jobs);

        } catch (HttpClientErrorException ex) {
            return "redirect:/candidate/login";
        }

        return "modules/candidate/jobs";
    }

    @PostMapping("/apply/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String applyJob(UUID jobId) {

        applyJobService.execute(getToken(), jobId);
        return "redirect:/candidate/jobs";
    }

    private String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails().toString();
    }

}

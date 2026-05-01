package br.com.jhonecmd.vacancy_management_front.modules.candidate.controllers;

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

import br.com.jhonecmd.vacancy_management_front.modules.candidate.services.CandidateService;
import br.com.jhonecmd.vacancy_management_front.modules.candidate.services.ProfileCandidateService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private ProfileCandidateService profileCandidateService;

    @GetMapping("/login")
    public String LoginCandidate() {
        return "modules/candidate/login";
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var result = profileCandidateService.execute(authentication.getDetails().toString());

        model.addAttribute("candidate", result);

        return "modules/candidate/profile";
    }

}

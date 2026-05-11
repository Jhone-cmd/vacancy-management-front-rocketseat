package br.com.jhonecmd.vacancy_management_front.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jhonecmd.vacancy_management_front.modules.company.dto.CreateCompanyDTO;
import br.com.jhonecmd.vacancy_management_front.modules.company.dto.CreateJobDTO;
import br.com.jhonecmd.vacancy_management_front.modules.company.services.CreateCompanyService;
import br.com.jhonecmd.vacancy_management_front.modules.company.services.CreateJobService;
import br.com.jhonecmd.vacancy_management_front.modules.company.services.LoginCompanyService;
import br.com.jhonecmd.vacancy_management_front.utils.FormatErrorMessage;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyService createCompanyService;

    @Autowired
    private LoginCompanyService loginCompanyService;

    @Autowired
    private CreateJobService createJobService;

    @GetMapping("/create")
    public String CreateCompany(Model model) {
        model.addAttribute("company", new CreateCompanyDTO());
        return "modules/company/create";
    }

    @PostMapping("/create")
    public String save(CreateCompanyDTO companyDTO, Model model) {

        try {
            createCompanyService.execute(companyDTO);
        } catch (HttpClientErrorException ex) {
            model.addAttribute("error", FormatErrorMessage.formatErrorMessage(ex.getResponseBodyAsString()));
        }

        model.addAttribute("company", companyDTO);
        return "modules/candidate/create";
    }

    @GetMapping("/login")
    public String LoginCompany() {
        return "modules/company/login";
    }

    @PostMapping("/signIn")
    public String singIn(RedirectAttributes redirectAttributes, HttpSession session, String email, String password) {
        try {

            var token = loginCompanyService.login(email, password);
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

            return "redirect:/company/jobs";

        } catch (HttpClientErrorException ex) {

            redirectAttributes.addFlashAttribute("error", "Credenciais Inválidas");
            return "redirect:/company/login";
        }

    }

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    public String jobs(Model model) {

        model.addAttribute("jobs", new CreateJobDTO());
        return "modules/company/jobs";
    }

    @PostMapping("/jobs")
    @PreAuthorize("hasRole('COMPANY')")
    public String createJob(CreateJobDTO jobs) {

        createJobService.execute(getToken(), jobs);
        return "redirect:/company/jobs";
    }

    @GetMapping("/jobs/list")
    @PreAuthorize("hasRole('COMPANY')")
    public String list() {
        return "modules/company/list";
    }

    private String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getDetails().toString();
    }

}

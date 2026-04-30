package br.com.jhonecmd.vacancy_management_front.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.jhonecmd.vacancy_management_front.modules.candidate.services.CandidateService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/login")
    public String LoginCandidate() {
        return "modules/candidate/login";
    }

    @PostMapping("/profile")
    public String singIn(RedirectAttributes redirectAttributes, String email, String password) {
        try {

            candidateService.login(email, password);
            return "modules/candidate/profile";

        } catch (HttpClientErrorException ex) {

            redirectAttributes.addFlashAttribute("error", "Credenciais Inválidas");
            return "redirect:/candidate/login";
        }

    }

}

package br.com.jhonecmd.vacancy_management_front.modules.candidate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @GetMapping("/login")
    public String LoginCandidate() {
        return "modules/candidate/login";
    }

    @PostMapping("/singIn")
    public String singIn(RedirectAttributes redirectAttributes, String email, String password) {

        if (email.equals("johndoe@email.com")) {
            return "modules/candidate/profile";
        }

        redirectAttributes.addFlashAttribute("error", "Credenciais Inválidas");

        return "redirect:/candidate/login";
    }

}

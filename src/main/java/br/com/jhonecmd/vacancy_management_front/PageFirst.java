package br.com.jhonecmd.vacancy_management_front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageFirst {

    @GetMapping("/home")
    public String pageFirstHTML(Model model) {

        model.addAttribute("message", "Primeira mensagem vindo do controller");
        return "PageFirst";
    }

    @GetMapping("/login")
    public String LoginCandidate() {
        return "modules/candidate/candidate-login";
    }

}

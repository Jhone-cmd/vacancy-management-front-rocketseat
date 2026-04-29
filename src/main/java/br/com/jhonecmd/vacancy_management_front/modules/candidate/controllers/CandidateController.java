package br.com.jhonecmd.vacancy_management_front.modules.candidate.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

    @GetMapping("/login")
    public String LoginCandidate() {
        return "modules/candidate/login";
    }
}

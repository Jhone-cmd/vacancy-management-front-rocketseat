package br.com.jhonecmd.vacancy_management_front.modules.company.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.jhonecmd.vacancy_management_front.modules.company.dto.CreateCompanyDTO;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @GetMapping("/create")
    public String CreateCompany(Model model) {
        model.addAttribute("company", new CreateCompanyDTO());
        return "modules/company/create";
    }
}

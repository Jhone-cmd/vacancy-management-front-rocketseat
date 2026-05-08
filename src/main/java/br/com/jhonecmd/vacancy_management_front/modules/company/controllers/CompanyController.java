package br.com.jhonecmd.vacancy_management_front.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import br.com.jhonecmd.vacancy_management_front.modules.company.dto.CreateCompanyDTO;
import br.com.jhonecmd.vacancy_management_front.modules.company.services.CreateCompanyService;
import br.com.jhonecmd.vacancy_management_front.utils.FormatErrorMessage;

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyService createCompanyService;

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
}

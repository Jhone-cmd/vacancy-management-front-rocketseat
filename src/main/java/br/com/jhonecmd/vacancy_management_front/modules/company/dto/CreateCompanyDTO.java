package br.com.jhonecmd.vacancy_management_front.modules.company.dto;

import lombok.Data;

@Data
public class CreateCompanyDTO {

    private String name;
    private String email;
    private String password;
    private String description;
    private String webSite;

}

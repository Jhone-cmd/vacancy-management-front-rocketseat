package br.com.jhonecmd.vacancy_management_front.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {
    private String name;
    private String benefits;
    private String description;
    private String level;
}

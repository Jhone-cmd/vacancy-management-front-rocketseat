package br.com.jhonecmd.vacancy_management_front.modules.company.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ListAllJobsDTO {

    private UUID id;
    private String name;
    private String description;
    private String benefits;
    private String level;
}

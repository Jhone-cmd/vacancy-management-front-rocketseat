package br.com.jhonecmd.vacancy_management_front.modules.candidate.dto;

import lombok.Data;

@Data
public class CreateCandidateDTO {
    private String name;
    private String email;
    private String password;
    private String description;
}

package br.com.jhonecmd.vacancy_management_front.modules.candidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListJobsDTO {

    private UUID id;
    private String name;
    private String description;
    private String benefits;
    private String level;
    private String companyName;
}

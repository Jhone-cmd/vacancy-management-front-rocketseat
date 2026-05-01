package br.com.jhonecmd.vacancy_management_front.modules.candidate.dto;

import java.util.List;

import lombok.Data;

@Data
public class Token {

    private String access_token;
    private Long expiresAt;
    private List<String> roles;
}

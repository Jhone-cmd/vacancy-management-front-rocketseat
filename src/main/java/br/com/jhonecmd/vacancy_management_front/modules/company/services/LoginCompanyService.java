package br.com.jhonecmd.vacancy_management_front.modules.company.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.company.dto.Token;

@Service
public class LoginCompanyService {

    public Token login(String email, String password) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("password", password);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

        var result = restTemplate.postForObject("http://localhost:8080/companies/auth", request, Token.class);
        return result;
    }
}

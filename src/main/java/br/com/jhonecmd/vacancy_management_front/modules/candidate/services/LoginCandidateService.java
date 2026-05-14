package br.com.jhonecmd.vacancy_management_front.modules.candidate.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.candidate.dto.Token;

@Service
public class LoginCandidateService {

    @Value("${api.url}")
    private String apiUrl;

    public Token login(String email, String password) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("password", password);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

        var url = apiUrl.concat("/candidates/auth");

        var result = restTemplate.postForObject(url, request, Token.class);

        return result;
    }

}

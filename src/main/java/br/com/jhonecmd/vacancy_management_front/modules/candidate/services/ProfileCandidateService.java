package br.com.jhonecmd.vacancy_management_front.modules.candidate.services;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.candidate.dto.ProfileCandidateDTO;

@Service
public class ProfileCandidateService {
    public ProfileCandidateDTO execute(@NonNull String token) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

        var result = restTemplate.exchange("http://localhost:8080/candidates/profile", HttpMethod.GET, request,
                ProfileCandidateDTO.class);

        return result.getBody();
    }
}

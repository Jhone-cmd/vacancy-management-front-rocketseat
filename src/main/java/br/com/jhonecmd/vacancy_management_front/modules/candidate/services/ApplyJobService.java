package br.com.jhonecmd.vacancy_management_front.modules.candidate.services;

import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplyJobService {

    public String execute(String token, UUID jobId) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<UUID> request = new HttpEntity<>(jobId, headers);

        var result = restTemplate.postForObject("http://localhost:8080/candidates/apply/job", request, String.class);
        System.out.println(result);
        return result;
    }

}

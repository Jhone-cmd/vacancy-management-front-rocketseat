package br.com.jhonecmd.vacancy_management_front.modules.candidate.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApplyJobService {

    @Value("${api.url}")
    private String apiUrl;

    public String execute(String token, UUID jobId) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<UUID> request = new HttpEntity<>(jobId, headers);

        var url = apiUrl.concat("/candidates/apply/job");

        var result = restTemplate.postForObject(url, request, String.class);
        return result;
    }

}

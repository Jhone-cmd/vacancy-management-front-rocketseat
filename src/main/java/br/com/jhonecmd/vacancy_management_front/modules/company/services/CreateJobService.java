package br.com.jhonecmd.vacancy_management_front.modules.company.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.company.dto.CreateJobDTO;

@Service
public class CreateJobService {

    @Value("${api.url}")
    private String apiUrl;

    public String execute(String token, CreateJobDTO jobDTO) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<CreateJobDTO> request = new HttpEntity<>(jobDTO, headers);

        var url = apiUrl.concat("/companies/jobs");

        var result = restTemplate.postForObject(url, request, String.class);
        return result;
    }
}

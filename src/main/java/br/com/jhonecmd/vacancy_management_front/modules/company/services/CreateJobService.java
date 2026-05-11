package br.com.jhonecmd.vacancy_management_front.modules.company.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.company.dto.CreateJobDTO;

@Service
public class CreateJobService {

    public String execute(String token, CreateJobDTO jobDTO) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<CreateJobDTO> request = new HttpEntity<>(jobDTO, headers);

        var result = restTemplate.postForObject("http://localhost:8080/companies/jobs", request, String.class);
        return result;
    }
}

package br.com.jhonecmd.vacancy_management_front.modules.candidate.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.candidate.dto.CreateCandidateDTO;

@Service
public class CreateCandidateService {

    public String execute(CreateCandidateDTO candidateDTO) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCandidateDTO> request = new HttpEntity<>(candidateDTO);

        var result = restTemplate.postForObject("http://localhost:8080/candidates", request, String.class);
        System.out.println(result);
        return result;
    }
}

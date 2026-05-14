package br.com.jhonecmd.vacancy_management_front.modules.candidate.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.candidate.dto.CreateCandidateDTO;

@Service
public class CreateCandidateService {

    @Value("${api.url}")
    private String apiUrl;

    public String execute(CreateCandidateDTO candidateDTO) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCandidateDTO> request = new HttpEntity<>(candidateDTO);

        var url = apiUrl.concat("/candidates");

        var result = restTemplate.postForObject(url, request, String.class);
        return result;
    }
}

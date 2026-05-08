package br.com.jhonecmd.vacancy_management_front.modules.company.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.company.dto.CreateCompanyDTO;

@Service
public class CreateCompanyService {
    public String execute(CreateCompanyDTO companyDTO) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCompanyDTO> request = new HttpEntity<>(companyDTO);

        var result = restTemplate.postForObject("http://localhost:8080/companies", request, String.class);
        return result;
    }
}

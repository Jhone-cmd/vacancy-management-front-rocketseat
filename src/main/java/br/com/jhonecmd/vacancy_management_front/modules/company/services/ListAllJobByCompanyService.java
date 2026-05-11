package br.com.jhonecmd.vacancy_management_front.modules.company.services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.company.dto.ListAllJobsDTO;

@Service
public class ListAllJobByCompanyService {

    public List<ListAllJobsDTO> execute(String token) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<ListAllJobsDTO>> responseType = new ParameterizedTypeReference<List<ListAllJobsDTO>>() {
        };

        var result = rt.exchange("http://localhost:8080/companies/jobs/list", HttpMethod.GET, httpEntity, responseType);
        return result.getBody();
    }
}

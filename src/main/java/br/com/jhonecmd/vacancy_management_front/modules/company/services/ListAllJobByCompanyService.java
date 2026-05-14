package br.com.jhonecmd.vacancy_management_front.modules.company.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.jhonecmd.vacancy_management_front.modules.company.dto.ListAllJobsDTO;

@Service
public class ListAllJobByCompanyService {

    @Value("${api.url}")
    private String apiUrl;

    public List<ListAllJobsDTO> execute(String token) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<ListAllJobsDTO>> responseType = new ParameterizedTypeReference<List<ListAllJobsDTO>>() {
        };

        var url = apiUrl.concat("/companies/jobs/list");

        var result = rt.exchange(url, HttpMethod.GET, httpEntity, responseType);
        return result.getBody();
    }
}

package br.com.jhonecmd.vacancy_management_front.modules.candidate.services;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jhonecmd.vacancy_management_front.modules.candidate.dto.ListJobsDTO;

import org.springframework.web.client.RestTemplate;

@Service
public class ListJobsService {

    public List<ListJobsDTO> execute(String token, String filter) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8080/candidates/jobs")
                .queryParam("filter", filter);

        ParameterizedTypeReference<List<ListJobsDTO>> responseType = new ParameterizedTypeReference<List<ListJobsDTO>>() {
        };

        try {
            var result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request,
                    responseType);

            System.out.println(result);
            return result.getBody();
        } catch (Unauthorized ex) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }
}

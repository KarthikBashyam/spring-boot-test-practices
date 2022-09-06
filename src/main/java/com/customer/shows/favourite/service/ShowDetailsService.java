package com.customer.shows.favourite.service;

import com.customer.shows.favourite.dto.ShowDetailsResponseDTO;
import com.customer.shows.favourite.util.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class ShowDetailsService {

    private RestTemplate restTemplate;

    private AppConfig appConfig;

    @Autowired
    public ShowDetailsService(RestTemplate restTemplate, AppConfig appConfig) {
        this.restTemplate = restTemplate;
        this.appConfig = appConfig;
    }
    public ShowDetailsResponseDTO getShowDetails(String showName) {

        var url = appConfig.getShowDetailsServiceUrl();

        var requestParams = new HashMap<String, String>();
        requestParams.put("showName", showName);
        ResponseEntity<ShowDetailsResponseDTO> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ShowDetailsResponseDTO>() {
        },requestParams);

        return responseEntity.getBody();
    }
}
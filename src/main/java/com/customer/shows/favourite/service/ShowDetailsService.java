package com.customer.shows.favourite.service;

import com.customer.shows.favourite.util.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ShowDetailsService {

    private RestTemplate restTemplate;

    private AppConfig appConfig;

    @Autowired
    public ShowDetailsService(RestTemplate restTemplate, AppConfig appConfig) {
        this.restTemplate = restTemplate;
        this.appConfig = appConfig;
    }
    public String getShowDetails(String showName) {

        var url = appConfig.getShowDetailsServiceUrl();

        var params = new HashMap<>();
        params.put("q",showName);
        System.out.println(url);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class, params);
        return responseEntity.getBody();
    }
}
package com.customer.shows.favourite.main;

import com.customer.shows.favourite.dto.ShowDetailsResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShowDetailsIntegrationTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void getShowDetails() {

        var params = new HashMap<String, String>();
        params.put("show","Fargo");

        ResponseEntity<ShowDetailsResponseDTO> response = restTemplate.exchange("/show/details?show={show}", HttpMethod.GET, null, new ParameterizedTypeReference<ShowDetailsResponseDTO>() {
        }, params);

        System.out.println("response.getBody() = " + response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}

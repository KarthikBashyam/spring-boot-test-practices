package com.customer.shows.favourite.main;

import com.customer.shows.favourite.domain.Customer;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerFavouriteShowsIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void createCustomer_withFavouriteShows() {

	}

	@Test
	void getCustomer_returnCustomerWithFavouriteShows() {
		ResponseEntity<Customer> response = restTemplate.getForEntity("/user/1", Customer.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
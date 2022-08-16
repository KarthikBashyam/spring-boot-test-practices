package com.customer.shows.favourite.main;

import com.customer.shows.favourite.dao.CustomerRepository;
import com.customer.shows.favourite.domain.Customer;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.customer.shows.favourite.dto.CustomerDTO;
import com.customer.shows.favourite.dto.FavouriteShowDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerFavouriteShowsIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	void createCustomer_withFavouriteShows() {

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setName("Karthik Bashyam");
		customerDTO.addFavouriteShow(new FavouriteShowDTO("Breaking Bad"));

		ResponseEntity<String> response = restTemplate.postForEntity("/customer", customerDTO, null);
		Optional<Customer> customer = customerRepository.findById(1l);
		assertThat(customer.get().getId()).isEqualTo(1l);
		assertThat(customer.get().getName()).isEqualTo("Karthik Bashyam");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Test
	void getCustomer_returnCustomerWithFavouriteShows() throws Exception {

		ResponseEntity<CustomerDTO> response = restTemplate.exchange("/customer/1", HttpMethod.GET, null, new ParameterizedTypeReference<CustomerDTO>() {
		});

		assertThat(response.getStatusCode()).isEqualTo(200);


	}
}
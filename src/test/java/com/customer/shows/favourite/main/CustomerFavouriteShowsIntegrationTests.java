package com.customer.shows.favourite.main;

import com.customer.shows.favourite.dao.CustomerRepository;
import com.customer.shows.favourite.domain.Customer;

import static org.assertj.core.api.Assertions.*;

import com.customer.shows.favourite.dto.CustomerDTO;
import com.customer.shows.favourite.dto.FavouriteShowDTO;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerFavouriteShowsIntegrationTests {

    private static GenericContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("hello")
            .withUsername("postgres")
            .withPassword("admin")
            .withReuse(true);


    @DynamicPropertySource
    private static void registerProperties(DynamicPropertyRegistry registry) {
        postgreSQLContainer.start();
        registry.add("spring.datasource.url", () -> String.format("jdbc:postgresql://localhost:%d/hello", postgreSQLContainer.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> "admin");
        registry.add("spring.jpa.database-platform",() -> "org.hibernate.dialect.PostgreSQLDialect");
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Order(1)
    void createCustomer_withFavouriteShows() {

        CustomerDTO expectedCustomerDTO = createCustomerDTO("Karthik Bashyam", "Fargo");

        ResponseEntity<Long> response = restTemplate.postForEntity("/customer", expectedCustomerDTO, Long.class);
        Optional<Customer> actualCustomer = customerRepository.findById(response.getBody());
        assertThat(actualCustomer.get().getId()).isEqualTo(response.getBody());
        assertThat(actualCustomer.get().getName()).isEqualTo(expectedCustomerDTO.getName());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    @Test
    @Order(2)
    void getCustomer_returnCustomerWithFavouriteShows() {
        ResponseEntity<String> response = restTemplate.exchange("/customer/15", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @NotNull
    private CustomerDTO createCustomerDTO(String customerName, String... showNames) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customerName);
        for (String show : showNames) {
            customerDTO.addFavouriteShow(new FavouriteShowDTO(show));
        }
        return customerDTO;
    }
}
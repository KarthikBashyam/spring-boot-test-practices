package com.customer.shows.favourite.main;

import com.customer.shows.favourite.dao.CustomerRepository;
import com.customer.shows.favourite.domain.Customer;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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

        CustomerDTO expectedCustomerDTO = createCustomerDTO("Karthik Bashyam", Arrays.asList("Fargo"));
        ResponseEntity<Long> response = createCustomer(expectedCustomerDTO);
        Optional<Customer> actualCustomer = findCustomerByIdInDB(response.getBody());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(actualCustomer.get().getId()).isEqualTo(response.getBody());
        assertThat(actualCustomer.get().getName()).isEqualTo(expectedCustomerDTO.getName());
    }

    @Test
    @Order(2)
    void getCustomer_returnCustomerWithFavouriteShows() {
        ResponseEntity<String> response = restTemplate.exchange("/customer/15", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
        });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @NotNull
    private CustomerDTO createCustomerDTO(String customerName, List<String> favShowsList) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customerName);
        for (String show : favShowsList) {
            customerDTO.addFavouriteShow(new FavouriteShowDTO(show));
        }
        return customerDTO;
    }

    private ResponseEntity<Long> createCustomer(CustomerDTO expectedCustomerDTO) {
        return restTemplate.postForEntity("/customer", expectedCustomerDTO, Long.class);
    }

    private Optional<Customer> findCustomerByIdInDB(Long id) {
        return customerRepository.findById(id);
    }

}
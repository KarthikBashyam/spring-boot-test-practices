package com.customer.shows.favourite.main;

import com.customer.shows.favourite.util.AppConfig;
import com.customer.shows.favourite.domain.Customer;
import com.customer.shows.favourite.dao.CustomerRepository;
import com.customer.shows.favourite.domain.FavouriteShow;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.customer.shows.favourite.*")
@EntityScan(basePackages = "com.customer.shows.favourite.domain")
@EnableJpaRepositories(basePackages = "com.customer.shows.favourite.*")
@EnableConfigurationProperties(AppConfig.class)
public class CustomerFavouriteShowsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerFavouriteShowsApplication.class, args);
	}

	@Bean
	CommandLineRunner startup(CustomerRepository userRepository) {
		return (args) -> {
			Customer customer = new Customer("Karthik");
			customer.addFavouriteShow(new FavouriteShow("Fargo"));
			userRepository.save(customer);
		};
	}

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);
		RestTemplate restTemplate = restTemplateBuilder.build();
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}
}
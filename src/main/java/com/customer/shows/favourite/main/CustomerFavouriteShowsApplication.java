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
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

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
			Customer user = new Customer("Karthik");
			user.addFavouriteShow(new FavouriteShow("Fargo"));
			userRepository.save(user);
		};
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
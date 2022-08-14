package com.customer.shows.favourite.util;

import com.customer.shows.favourite.domain.Customer;
import com.customer.shows.favourite.domain.FavouriteShow;
import com.customer.shows.favourite.dto.CustomerDTO;
import com.customer.shows.favourite.dto.FavouriteShowDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public Customer mapFromDTOtoEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customerDTO.getFavouriteShowList().stream().map(this::mapFavouriteShowDTO).forEach(customer::addFavouriteShow);
        return customer;
    }

    public CustomerDTO mapFromEntityTODTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customer.getFavouriteShowsList().stream().map(this::mapFavouriteShowDTO).forEach(customerDTO::addFavouriteShow);
        return customerDTO;
    }

    private FavouriteShow mapFavouriteShowDTO(FavouriteShowDTO favouriteShowDTO) {
        FavouriteShow favouriteShow = new FavouriteShow();
        favouriteShow.setShowName(favouriteShowDTO.getShowName());
        return favouriteShow;
    }

    private FavouriteShowDTO mapFavouriteShowDTO(FavouriteShow favouriteShow) {
        FavouriteShowDTO favouriteShowDTO = new FavouriteShowDTO();
        favouriteShowDTO.setShowName(favouriteShow.getShowName());
        return favouriteShowDTO;
    }
}

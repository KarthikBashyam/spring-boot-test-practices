package com.customer.shows.favourite.mapper;

import com.customer.shows.favourite.domain.Customer;
import com.customer.shows.favourite.domain.FavouriteShow;
import com.customer.shows.favourite.dto.CustomerDTO;
import com.customer.shows.favourite.dto.FavouriteShowDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer mapToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customerDTO.getFavouriteShowList().stream().map(this::mapToEntity).forEach(customer::addFavouriteShow);
        return customer;
    }

    public CustomerDTO mapToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customer.getFavouriteShowsList().stream().map(this::mapToDTO).forEach(customerDTO::addFavouriteShow);
        return customerDTO;
    }

    private FavouriteShow mapToEntity(FavouriteShowDTO favouriteShowDTO) {
        FavouriteShow favouriteShow = new FavouriteShow();
        favouriteShow.setShowName(favouriteShowDTO.getShowName());
        return favouriteShow;
    }

    private FavouriteShowDTO mapToDTO(FavouriteShow favouriteShow) {
        FavouriteShowDTO favouriteShowDTO = new FavouriteShowDTO();
        favouriteShowDTO.setShowName(favouriteShow.getShowName());
        return favouriteShowDTO;
    }
}

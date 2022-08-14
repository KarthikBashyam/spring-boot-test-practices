package com.customer.shows.favourite.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerDTO {

    private String name;

    private List<FavouriteShowDTO> favouriteShowList;

    public CustomerDTO() {
        this.favouriteShowList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FavouriteShowDTO> getFavouriteShowList() {
        return favouriteShowList;
    }

    public void setFavouriteShowList(List<FavouriteShowDTO> favouriteShowList) {
        this.favouriteShowList = favouriteShowList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void addFavouriteShow(FavouriteShowDTO favouriteShowDTO) {
        this.favouriteShowList.add(favouriteShowDTO);
    }
}

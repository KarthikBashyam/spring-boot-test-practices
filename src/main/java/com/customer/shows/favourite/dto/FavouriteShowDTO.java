package com.customer.shows.favourite.dto;

public class FavouriteShowDTO {
    private String showName;

    public FavouriteShowDTO(String showName) {
        this.showName = showName;
    }

    public FavouriteShowDTO() {
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}

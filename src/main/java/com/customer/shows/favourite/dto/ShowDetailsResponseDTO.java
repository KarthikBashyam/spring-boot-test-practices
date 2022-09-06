package com.customer.shows.favourite.dto;

import lombok.Data;

@Data
public class ShowDetailsResponseDTO {

    private String id;

    private String name;

    private String type;

    private String language;

    private String status;

    private Integer runtime;

    public ShowDetailsResponseDTO() {
    }
}

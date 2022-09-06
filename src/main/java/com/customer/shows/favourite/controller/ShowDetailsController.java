package com.customer.shows.favourite.controller;

import com.customer.shows.favourite.dto.ShowDetailsResponseDTO;
import com.customer.shows.favourite.service.ShowDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShowDetailsController {

    private ShowDetailsService showDetailsService;

    @Autowired
    public ShowDetailsController(ShowDetailsService showDetailsService) {
        this.showDetailsService = showDetailsService;
    }

    @GetMapping("/show/details")
    public ResponseEntity<ShowDetailsResponseDTO> getShowDetails(@RequestParam("show") String shownName) {
        ShowDetailsResponseDTO detailsResponseDTO = showDetailsService.getShowDetails(shownName);
        return ResponseEntity.ok(detailsResponseDTO);
    }
}

package com.customer.shows.favourite.controller;

import com.customer.shows.favourite.service.ShowDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getShowDetails(@RequestParam("show") String shownName) {
        return showDetailsService.getShowDetails(shownName);
    }
}

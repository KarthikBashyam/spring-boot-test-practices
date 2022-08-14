package com.customer.shows.favourite.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
public class AppConfig {

    private String showDetailsServiceUrl;

    public String getShowDetailsServiceUrl() {
        return showDetailsServiceUrl;
    }

    public void setShowDetailsServiceUrl(String showDetailsServiceUrl) {
        this.showDetailsServiceUrl = showDetailsServiceUrl;
    }
}

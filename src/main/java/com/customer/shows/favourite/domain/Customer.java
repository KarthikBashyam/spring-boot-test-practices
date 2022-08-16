package com.customer.shows.favourite.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FavouriteShow> favouriteShowsList = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FavouriteShow> getFavouriteShowsList() {
        return favouriteShowsList;
    }

    public void setFavouriteShowsList(List<FavouriteShow> favouriteShowsList) {
        this.favouriteShowsList = favouriteShowsList;
    }

    public void addFavouriteShow(FavouriteShow favouriteShow) {
        this.favouriteShowsList.add(favouriteShow);
        favouriteShow.setUser(this);
    }
}

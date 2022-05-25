package com.kitchen_anywhere.kitchen_anywhere.model;

import java.util.List;

public class AddFoodModel {

    private String dishTitle;
    private String description;
    private String typeOfDish;
    private Double price;
    private String dishImageLink;
    private String chef_id;
    private String postal_code;
    private int categoryId;
    private List<String> favouriteUserID;
    private int star;
    private boolean isActive;
    private boolean isVegetarian;
    private Double maxLimit ;
    private Double pendingLimit ;
    private int qty;
    public AddFoodModel(String dishTitle, String description, String typeOfDish, Double price, String dishImageLink, int star,
                     String chef_id,List<String> favouriteUserID,int categoryId,
                     Double maxLimit, Double pendingLimit,boolean isActive,boolean isVegetarian,String postal_code
    ) {

        this.dishTitle = dishTitle;
        this.description = description;
        this.typeOfDish = typeOfDish;
        this.price = price;
        this.dishImageLink = dishImageLink;
        this.star = star;
        this.chef_id = chef_id ;
        this.favouriteUserID = favouriteUserID;
        this.categoryId = categoryId ;
        this.maxLimit = maxLimit;
        this.pendingLimit = pendingLimit ;
        this.isActive = isActive;
        this.isVegetarian = isVegetarian ;
        this.postal_code = postal_code ;
    }
}

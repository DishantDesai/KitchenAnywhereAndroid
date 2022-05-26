package com.kitchen_anywhere.kitchen_anywhere.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class FoodModel implements Serializable
{
    private String id;
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
    public FoodModel(String id,String dishTitle, String description, String typeOfDish, Double price, String dishImageLink, int star,
                     String chef_id,List<String> favouriteUserID,int categoryId,
                     Double maxLimit, Double pendingLimit,boolean isActive,boolean isVegetarian,String postal_code
                     ) {
        this.id = id;
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
    public String  getId() {
        return id;
    }

    public boolean getisVegetarian() {
        return isVegetarian;
    }

    public void setisVegetarian(boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    public boolean getisActive() {
        return isActive;
    }

    public void setisActive(boolean isActive) {
        this.isActive = isActive;
    }


    public Double getpending_limit() {
        return pendingLimit;
    }

    public void setpending_limit(Double pendingLimit) {
        this.pendingLimit = pendingLimit;
    }

    public Double getmaxLimit() {
        return maxLimit;
    }

    public void setmaxLimit(Double maxLimit) {
        this.maxLimit = maxLimit;
    }
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public List<String> getfavouriteUserID() {
        return favouriteUserID;
    }

    public void setfavouriteUserID(List<String> favouriteUserID) {
        this.favouriteUserID = favouriteUserID;
    }


    public String getdishTitle() {
        return dishTitle;
    }

    public void setdishTitle(String dishTitle) {
        this.dishTitle = dishTitle;
    }

    public String getChef_id() {
        return chef_id;
    }

    public void setChef_id(String chef_id) {
        this.chef_id = chef_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String gettypeOfDish() {
        return typeOfDish;
    }

    public void settypeOfDish(String typeOfDish) {
        this.typeOfDish = typeOfDish;
    }

    public String getpostal_code() {
        return postal_code;
    }

    public void setpostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getdishImageLink() {
        return dishImageLink;
    }

    public void setdishImageLink(String dishImageLink) {
        this.dishImageLink = dishImageLink;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
}

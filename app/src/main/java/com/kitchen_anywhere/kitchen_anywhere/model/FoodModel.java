package com.kitchen_anywhere.kitchen_anywhere.model;

public class FoodModel
{
    private String title;
    private String description;
    private String type;
    private Double price;
    private String image;
    private String chef_id;
    private int star;

    public FoodModel(String title, String description, String type, Double price, String image, int star,String chef_id) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.price = price;
        this.image = image;
        this.star = star;
        this.chef_id = chef_id ;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}

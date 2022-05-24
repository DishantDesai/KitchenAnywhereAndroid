package com.kitchen_anywhere.kitchen_anywhere.model;

public class PostalCodeModel {

    String postal_code,city,province;
    int distance;

    public PostalCodeModel(String postal_code, String city, String province, int distance) {
        this.postal_code = postal_code;
        this.city = city;
        this.province = province;
        this.distance = distance;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public int getDistance() {
        return distance;
    }


}

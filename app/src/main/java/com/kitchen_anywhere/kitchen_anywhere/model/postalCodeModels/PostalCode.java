package com.kitchen_anywhere.kitchen_anywhere.model.postalCodeModels;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class PostalCode {

    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("city")
    @Expose
    private transient String city;
    @SerializedName("province")
    @Expose
    private String province;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
package com.kitchen_anywhere.kitchen_anywhere.model.postalCodeModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("jsonschema2pojo")
public class PostalObject {

    @SerializedName("postal_codes")
    @Expose
    public List<PostalCode> postalCodes = null;

    public List<PostalCode> getPostalCodes() {
        return postalCodes;
    }

    public void setPostalCodes(List<PostalCode> postalCodes) {
        this.postalCodes = postalCodes;
    }

}
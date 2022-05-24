package com.kitchen_anywhere.kitchen_anywhere.API;

import com.kitchen_anywhere.kitchen_anywhere.model.PostalCodeModel;
import com.kitchen_anywhere.kitchen_anywhere.model.postalCodeModels.PostalObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostalApi {

    @GET("km/")
    Call<List<PostalObject>> getPostalCodes();

}

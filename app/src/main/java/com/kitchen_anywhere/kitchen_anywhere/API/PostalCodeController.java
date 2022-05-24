package com.kitchen_anywhere.kitchen_anywhere.API;


import com.kitchen_anywhere.kitchen_anywhere.model.PostalCodeModel;
import com.kitchen_anywhere.kitchen_anywhere.model.postalCodeModels.PostalCode;
import com.kitchen_anywhere.kitchen_anywhere.model.postalCodeModels.PostalObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostalCodeController {

    String postalCode;
    static String URL;


    public void setup(String postalCode){

        this.postalCode = postalCode;
        System.out.println("------------------- first --------------");
        URL = "https://www.zipcodeapi.com/rest/v2/CA/tQyUhg0I9eNTLNiBQgRrFw1BlAZUx2dw5T97WK9bw5GqXhVGJ0YTM44T0CcCKrt7/radius.json/"+this.postalCode+"/0.5/";

        // create retrofit object
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        System.out.println("------------------- second --------------");
        //Convert json data to model class object
        PostalApi postalApi= retrofit.create(PostalApi.class);

        System.out.println("------------------- third --------------");
        // create a call of model class
        Call<List<PostalObject>> call = postalApi.getPostalCodes();



        System.out.println("------------------- start --------------" + call);
        //processing the data and receive the response
        call.enqueue(new Callback<List<PostalObject>>() {

            @Override
            public void onResponse(Call<List<PostalObject>> call, Response<List<PostalObject>> response) {
                System.out.println("------------------- ----------------sss --------------");
                List<PostalObject> data = response.body();
                System.out.println("------------------- starting --------------" + data.size());
                for (PostalObject postals : data) {
                    System.out.println("------------------- in --------------");
                    System.out.println("------------------- starting --------------" + postals.postalCodes.size());
                    for (PostalCode pc: postals.postalCodes) {
                        System.out.println("Codes : " + pc.getPostalCode());
                    }

                }
            }

            @Override
            public void onFailure(Call<List<PostalObject>> call, Throwable t) {
                System.out.println("------------------- error --------------"+t.getLocalizedMessage());
            }
        });

    }







}

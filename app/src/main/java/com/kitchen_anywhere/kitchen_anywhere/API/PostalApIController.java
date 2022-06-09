package com.kitchen_anywhere.kitchen_anywhere.API;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.kitchen_anywhere.kitchen_anywhere.interfaces.redirection;
import com.kitchen_anywhere.kitchen_anywhere.model.postalCodeModels.PostalCode;
import com.kitchen_anywhere.kitchen_anywhere.model.postalCodeModels.PostalObject;

import java.util.ArrayList;
import java.util.List;

public class PostalApIController {

    String postalCode;
    static String URL;
    public ArrayList<PostalCode> postalList;




    public StringRequest searchPostalRequest(String postalCode, redirection ctxRedirection) {

        this.postalCode = postalCode;
        System.out.println("------------------- first --------------");
        URL = "https://www.zipcodeapi.com/rest/v2/CA/yQRfaPCULMR42Uk6mp2QSWGggGrb9FR0vStkqhdFxHnmhbs8ZQ9Lv9dEZLPmkjcW/radius.json/"+this.postalCode+"/0.2/km";
System.out.println(URL);
        postalList = new ArrayList<PostalCode>();

        // 1st param => type of method (GET/PUT/POST/PATCH/etc)
        // 2nd param => complete url of the API
        // 3rd param => Response.Listener -> Success procedure
        // 4th param => Response.ErrorListener -> Error procedure
        return new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    // 3rd param - method onResponse lays the code procedure of success return
                    // SUCCESS
                    @Override
                    public void onResponse(String response) {
                        // try/catch block for returned JSON data
                        // see API's documentation for returned format
//                        System.out.println("-------stat"+response);
                        response = response.replace("\"city\":", "\"city\":\"a\"");
                        postalList.clear();
//                        String response1 = new Gson().toJson(response);
                        Gson gson = new Gson();
                        PostalObject parsedResponse = gson.fromJson(response, PostalObject.class);
//                        List<PostalCode> pc = parsedResponse.getPostalCodes();
                        for (PostalCode pcObj:
                             parsedResponse.getPostalCodes()) {
                            postalList.add(pcObj);
                            System.out.println(pcObj.getPostalCode());
                        }
                        System.out.println(postalList.size() + "~~~~~~~~~~~~~~~~~~~~~~~~~`");

                        ctxRedirection.callFucntion();

                    } // public void onResponse(String response)
                }, // Response.Listener<String>()
                new Response.ErrorListener() {
                    // 4th param - method onErrorResponse lays the code procedure of error return
                    // ERROR
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // display a simple message on the screen
                        System.out.println("errrroro of response -----"  + error.getLocalizedMessage());
                    }
                });
    }



}

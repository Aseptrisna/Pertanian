package com.example.hans.agrigo.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestService {
    private static final String BASK_URL = "http://worker1.server.pptik.id:8080/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null){

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASK_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static String inputDataNutrisi = BASK_URL + "/inputdata";

}

package com.example.hans.agrigo.MenuScanBarcode.Support;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServerDevice {


    public static final String BASE_URL ="http://192.168.21.198:8080/";
    private static ServerDevice mInstance;
    private Retrofit retrofit;

    private ServerDevice(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized ServerDevice getInstance(){
        if (mInstance == null ){
            mInstance = new ServerDevice();
        }
        return mInstance;
    }

    public ApiService getApi(){
        return retrofit.create(ApiService.class);
    }


}

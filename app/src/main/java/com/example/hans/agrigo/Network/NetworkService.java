package com.example.hans.agrigo.Network;

import com.example.hans.agrigo.MenuLogin.Model.LoginRespon;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkService {

    @POST("users/login")
    Call<LoginRespon> login();
    @FormUrlEncoded
    @POST("users/register")
    Call<LoginRespon> signup(@FieldMap Map<String,Object> params);
//    @GET("/map")
//    Call<Data> view();
}

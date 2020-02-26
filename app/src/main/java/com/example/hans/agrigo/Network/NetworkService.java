package com.example.hans.agrigo.Network;


import com.example.hans.agrigo.MenuLogin.Login_Response;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkService {


    @FormUrlEncoded
    @POST("users/login")
    Call<ResponseBody> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    //    regis user
    @FormUrlEncoded
    @POST("users")
    Call<ResponseBody> RegsiterUser(
            @Field("guid") String d_guid,
            @Field("no_ktp") String d_ktp,
            @Field("nama") String d_nama,
            @Field("no_hp") String d_tlp,
            @Field("alamat") String d_alamat,
            @Field("email") String d_email,
            @Field("password") String d_password,
            @Field("latitude") String d_lat,
            @Field("longitude") String d_long
    );

    //   device
    @FormUrlEncoded
    @POST("devices")
    Call<ResponseBody>Device (
            @Field("devices_mac_address") String d_mac,
            @Field("devices_name") String d_namadevice,
            @Field("devices_code") String d_devicecode
    );

}

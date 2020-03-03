package com.example.hans.agrigo.Network;


/*
Create By Asep Trisna Setiawan
Bandung 2020
 */

import android.renderscript.Sampler;

import com.example.hans.agrigo.MenuLogin.Login_Response;
import com.example.hans.agrigo.Storage.SharedPrefManager;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface NetworkService {
    SharedPrefManager sharedPrefManager = null;
    String id=sharedPrefManager.getSpId();


    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseBody> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    //    regis user
    @FormUrlEncoded
    @POST("user/register")
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

    @FormUrlEncoded
    @POST("device/aktivasi/5e5c83919cc0d71414d31bcb")
    Call<ResponseBody>Aktivasi_Device (
            @Field("macAddress") String d_mac,
            @Field("deviceCode") String d_code
    );
}


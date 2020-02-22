package com.example.hans.agrigo.MenuScanBarcode.Support;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {


    //    regis device
    @FormUrlEncoded
    @POST("users")
    Call<ResponseBody> RegsiterUser(
            @Field("guid") String d_guid,
            @Field("no_ktp") String d_ktp,
            @Field("nama") String d_nama,
            @Field("no_hp") String d_tlp,
            @Field("email") String d_alamat,
            @Field("alamat") String d_email,
            @Field("password") String d_password,
            @Field("latitude") String d_lat,
            @Field("longitude") String d_long
    );

//    registeruser
    @FormUrlEncoded
    @POST("insertmac")
     Call<ResponseBody>Device (
        @Field("IDDevice") String d_mac,
        @Field("namaAlat") String d_namadevice
);
}

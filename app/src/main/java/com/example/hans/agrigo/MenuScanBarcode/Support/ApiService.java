package com.example.hans.agrigo.MenuScanBarcode.Support;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {


    //    regis device
    @FormUrlEncoded
    @POST("insertmac")
    Call<ResponseBody> Device(
            @Field("IDDevice") String d_mac,
            @Field("namaAlat") String d_namadevice
    );

}

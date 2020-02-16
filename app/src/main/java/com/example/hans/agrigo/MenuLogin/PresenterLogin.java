package com.example.hans.agrigo.MenuLogin;

import android.util.Base64;
import android.util.Log;

import com.example.hans.agrigo.MenuLogin.Model.LoginRespon;
import com.example.hans.agrigo.Network.NetworkService;
import com.example.hans.agrigo.Network.RestService;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PresenterLogin {
    final IAuthLogin view;
    private final Retrofit restService;

    PresenterLogin(Login view){
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void login(String username,String password) {
        String credentials = username + ":" + password;
        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//        System.out.print(basic);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", basic)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        }).build();

        restService.newBuilder().client(okHttpClient).build()
                .create(NetworkService.class).login().enqueue(new Callback<LoginRespon>() {

            @Override
            public void onResponse(Call<LoginRespon> call, retrofit2.Response<LoginRespon> response) {
                Log.d("msg","" + response.body().getMessage()+ response.body().getName()+ response.body().getName2()+ response.body().getMacSensor());
                if (response.body().getSuccess()) {
                    view.onSigninSucces(response.body().getMessage(),response.body().getName(),response.body().getName2(),response.body().getMacSensor());
                } else {
                    view.onSigninFailed();
                }
            }

            @Override
            public void onFailure(Call<LoginRespon> call, Throwable t) {
                view.onNetworkFailed(t.getLocalizedMessage());

            }

        });
    }
}


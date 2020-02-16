package com.example.hans.agrigo.MenuRegister;

import android.util.Log;

import com.example.hans.agrigo.MenuRegister.Model.RegisterModel;
import com.example.hans.agrigo.MenuLogin.Model.LoginRespon;
import com.example.hans.agrigo.Network.NetworkService;
import com.example.hans.agrigo.Network.RestService;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class PresenterRegis {

    final IAuthRegis view;
    private final Retrofit restService;

    PresenterRegis(IAuthRegis view){
        this.view = view;
        restService = RestService.getRetrofitInstance();
    }

    void signup(RegisterModel registerModel) {
        String email    = registerModel.getEmail();
        String name2    = registerModel.getName2();
        String alamat   = registerModel.getAlamat();
        String nomor    = registerModel.getNomor();
        String username = registerModel.getUsername();
        String password = registerModel.getPassword();
        String macSensor = registerModel.getMacSensor();
        String macRelay = registerModel.getMacRelay();

        HashMap<String, Object> params = new HashMap<>();
        params.put("name", username);
        params.put("name2", name2);
        params.put("nomor", nomor);
        params.put("alamat", alamat);
        params.put("email", email);
        params.put("password", password);
        params.put("macSensor", macSensor);
        params.put("macRelay", macRelay);
        Log.i("msg", "" +username+password+email);

        restService.create(NetworkService.class).signup(params).enqueue(new Callback<LoginRespon>() {

            @Override
            public void onResponse(Call<LoginRespon> call, retrofit2.Response<LoginRespon> response) {
                Log.d("msgny","" + response.body().getSuccess());
                if (response.body().getSuccess()) {
                    view.onSignupSucces();
                } else {
                    view.onSignupFailed();
                }
            }

            @Override
            public void onFailure(Call<LoginRespon> call, Throwable t) {
                view.onNetworkFailed(t.getLocalizedMessage());

            }

        });
    }
}

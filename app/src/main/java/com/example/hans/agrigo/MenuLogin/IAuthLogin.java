package com.example.hans.agrigo.MenuLogin;

public interface IAuthLogin {
    void InitView();

    void Signin();

    void onSigninSucces(String email, String name, String name2, String macSensor);

    void onSigninFailed();

    void onNetworkFailed(
            String cause
    );
}

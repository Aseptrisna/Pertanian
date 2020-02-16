package com.example.hans.agrigo.MenuRegister;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hans.agrigo.MenuLogin.Login;
import com.example.hans.agrigo.MenuRegister.Model.RegisterModel;
import com.example.hans.agrigo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Registerr extends AppCompatActivity implements IAuthRegis{

    PresenterRegis presenterRegis;

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.name2)
    EditText name2;

    @BindView(R.id.nomor)
    EditText nomor;

    @BindView(R.id.alamat)
    EditText alamat;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.macSensor)
    EditText macSensor;

    @BindView(R.id.macRelay)
    EditText macRelay;

    @BindView(R.id.btnRegister)
    Button btnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        presenterRegis = new PresenterRegis(this);
        this.InitView();
    }

    @Override
    public void InitView(){
        btnRegis.setOnClickListener(v->this.Signup());
    }

    @Override
    public void Signup(){
        RegisterModel registerModel = new RegisterModel();
        registerModel.setEmail(email.getText().toString());
        registerModel.setName2(name2.getText().toString());
        registerModel.setNomor(nomor.getText().toString());
        registerModel.setAlamat(alamat.getText().toString());
        registerModel.setUsername(username.getText().toString());
        registerModel.setPassword(password.getText().toString());
        registerModel.setMacSensor(macSensor.getText().toString());
        registerModel.setMacRelay(macRelay.getText().toString());

        presenterRegis.signup(registerModel);
    }

    @Override
    public void onSignupSucces(){
        Intent a = new Intent(Registerr.this, Login.class);
        startActivity(a);
        finish();
        Toast.makeText(this, "Berhasil Terdaftar", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignupFailed(){
        Toast.makeText(this, "Gagal Mendaftar", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNetworkFailed(String cause) {
        Toast.makeText(Registerr.this, cause, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnLogin
    )
    void btnRegis(){
        Intent b = new Intent(Registerr.this, Login.class);
        startActivity(b);
        finish();
    }
}

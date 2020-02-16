package com.example.hans.agrigo.MenuLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hans.agrigo.Menu.MenuUtama;
import com.example.hans.agrigo.MenuRegister.Registerr;
import com.example.hans.agrigo.MenuScanBarcode.AddDevice;
import com.example.hans.agrigo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity implements IAuthLogin{
    PresenterLogin presenter;
    SharedPreferences sharedPreferences;
    public static final String session_status = "session_status";
    public static final String my_shared_preferences = "my_shared_preferences";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_EMAIL = "email";
    public final static String TAG_NAME = "name";
    public final static String TAG_NAME2 = "name2";
    public final static String KEY_MAC = "macSensor";

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.btnSignin)
    Button btnLogin;

    String name, email, name2, macSensor;
    Boolean session = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new PresenterLogin(this);
        this.InitView();

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        email = sharedPreferences.getString(TAG_EMAIL, null);
        name = sharedPreferences.getString(TAG_NAME, null);
        name2 = sharedPreferences.getString(TAG_NAME2, null);
        macSensor = sharedPreferences.getString(KEY_MAC, null);

        if (session){
            Intent a = new Intent(Login.this, MenuUtama.class);
            startActivity(a);
            finish();
        }
    }

    @Override
    public void InitView(){
        btnLogin.setOnClickListener(v->this.Signin());
    }

    @Override
    public void Signin(){
        presenter.login(
                username.getText().toString(),
                password.getText().toString());
}


    @Override
    public void onSigninSucces(String email, String name, String name2, String macSensor){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(session_status, true);
        editor.putString(TAG_EMAIL, email);
        editor.putString(TAG_NAME, name );
        editor.putString(TAG_NAME2, name2 );
        editor.putString(KEY_MAC, macSensor );
        editor.commit();

        Intent a = new Intent(Login.this, AddDevice.class);
        startActivity(a);
        finish();
        //Toast.makeText(this,"Hello "+ name+" " + name2, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSigninFailed(){
        Toast.makeText(this, "Username or password is wrong", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNetworkFailed(String cause) {
        Toast.makeText(Login.this, "Sambungan Network Terputus." + cause, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.btnRegis)
    void btnRegis(){
        Intent b = new Intent(Login.this, Registerr.class);
        startActivity(b);
        finish();
    }
}

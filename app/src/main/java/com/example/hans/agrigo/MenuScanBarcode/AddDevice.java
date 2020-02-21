package com.example.hans.agrigo.MenuScanBarcode;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.hans.agrigo.Menu.MenuUtama;
import com.example.hans.agrigo.MenuLogin.Login;
import com.example.hans.agrigo.MenuScanBarcode.Support.DeviceRespon;
import com.example.hans.agrigo.MenuScanBarcode.Support.ServerDevice;
import com.example.hans.agrigo.Network.RestService;
import com.example.hans.agrigo.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDevice extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.scan)
    Button ScanBarcode;
    @BindView(R.id.adddevice)
    Button Tambahdevice;
    @BindView(R.id.id_device)
    EditText IDDevice;
    @BindView(R.id.mac)
    EditText Mac;
    @BindView(R.id.namaDevice)
    EditText Namadevice;
    @BindView(R.id.main)
    RelativeLayout main;
    @BindView(R.id.cv)
    CardView formdevice;
    ProgressDialog loading;
    private IntentIntegrator intentIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        ButterKnife.bind(this);
        ScanBarcode.setOnClickListener(this);
        Tambahdevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Namadevice.getText().toString().equals("") || IDDevice.getText().toString().equals("")) {
                    Namadevice.setFocusable(false);
                    IDDevice.setFocusable(false);
                    showSnackbar();
                } else {
                    loading = ProgressDialog.show(AddDevice.this,"Loading.....",null,true,true);
                    RegisterDevice();
                }
            }
        });

//        formdevice=(CardView)findViewById(R.id.sv);
        formdevice.setVisibility(View.GONE);
        Tambahdevice.setVisibility(View.GONE);
        ScanBarcode.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
            } else {
                // jika qrcode berisi data
                try {
                    // converting the data json
                    JSONObject object = new JSONObject(result.getContents());
                    // atur nilai ke textviews
                    Mac.setText(object.getString("mac"));
                    IDDevice.setText(object.getString("devicecode"));
                    Namadevice.setText(object.getString("devicename"));
                    formdevice.setVisibility(View.VISIBLE);
                    Tambahdevice.setVisibility(View.VISIBLE);

//                    Toast.makeText(this, (object.getString("nama")), Toast.LENGTH_SHORT).show();
//                    textViewTinggi.setText(object.getString("tinggi"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    // jika format encoded tidak sesuai maka hasil
                    // ditampilkan ke toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        // inisialisasi IntentIntegrator(scanQR)
        intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    public void onBackPressed() {
        super.onBackPressed();
        goBackMenu();
    }

    public void goBackMenu() {
        startActivity(new Intent(AddDevice.this, Login.class));
        finish();
    }


    private void RegisterDevice() {

        String d_mac = String.valueOf(IDDevice.getText());
        String d_namadevice = String.valueOf(Namadevice.getText());

        if (d_namadevice.equals("")) {
            showSnackbar();
        } else if (d_mac.equals("")) {
            showSnackbar();
        } else {
            retrofit2.Call<ResponseBody> call = ServerDevice.getInstance().getApi().Device(d_mac,d_namadevice);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
                                loading.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("err").equals("false")) {
                            Log.d("responsenya", response.body().toString());
                            Intent intent = new Intent(AddDevice.this, MenuUtama.class);
                            startActivity(intent);
                            finish();
                        } else {
                            String error_message = jsonRESULTS.getString("msg");
                            Toast.makeText(AddDevice.this, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }

                }else{
                        loading.dismiss();

                    }

                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("responsenya", t.toString());
                    Toast.makeText(AddDevice.this,t.toString(),Toast.LENGTH_LONG).show();
                    loading.dismiss();
                }
            });
        }



    }

    public void showSnackbar() {
        Snackbar snackbar = Snackbar.make(main, "Harus disi", Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(main, "Silahkan ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        Namadevice.setFocusableInTouchMode(true);
                        IDDevice.setFocusableInTouchMode(true);
//                        jumlahbeli.setFocusableInTouchMode(true);
//                        Password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }
}
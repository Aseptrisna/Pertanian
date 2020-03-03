package com.example.hans.agrigo.MenuScanBarcode;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.hans.agrigo.Menu.MenuUtama;
import com.example.hans.agrigo.MenuLogin.Login;
import com.example.hans.agrigo.Network.InitRetrofit;
import com.example.hans.agrigo.R;
import com.example.hans.agrigo.Storage.SharedPrefManager;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDevice extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.scan)
    Button ScanBarcode;
//    @BindView(R.id.adddevice)
//    Button Tambahdevice;
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

    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    private IntentIntegrator intentIntegrator;
    String Mac_Addres,DeviceName,Device_Code,RandomCode,Type;

    String user = "iot_pertanian";
    String pass = "iotpertanian";
    String host = "167.205.7.226";
    String vhost = "/iotpertanian";
    ConnectionFactory factory = new ConnectionFactory();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        ButterKnife.bind(this);
        ScanBarcode.setOnClickListener(this);
        sharedPrefManager = new SharedPrefManager(this);

//        Tambahdevice.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (Namadevice.getText().toString().equals("") || IDDevice.getText().toString().equals("")) {
//                    Namadevice.setFocusable(false);
//                    IDDevice.setFocusable(false);
//                    showSnackbar();
//                } else {
//                    loading = ProgressDialog.show(AddDevice.this,"Loading.....",null,true,true);
//                    RegisterDevice();
//                }
//            }
//        });

//        formdevice=(CardView)findViewById(R.id.sv);
        formdevice.setVisibility(View.GONE);
//        Tambahdevice.setVisibility(View.GONE);
        ScanBarcode.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Hasil tidak ditemukan,Ulangi Kembali", Toast.LENGTH_SHORT).show();
            } else {
                // jika qrcode berisi data
                try {
                    // converting the data json
                    JSONObject object = new JSONObject(result.getContents());
//                    Mac.setText(object.getString("mac"));
//                    IDDevice.setText(object.getString("devicecode"));
//                    Namadevice.setText(object.getString("devicename"));
//                    formdevice.setVisibility(View.VISIBLE);
//                    Tambahdevice.setVisibility(View.VISIBLE);
                    Mac_Addres=object.getString("mac");
//                    Toast.makeText(this, ""+Mac_Addres, Toast.LENGTH_SHORT).show();
//                    DeviceName=object.getString("devicename");
                    Device_Code= object.getString("devicetype");
                    int code = (int) ((new Date().getTime() / 1000L)%Integer.MIN_VALUE);
                    if(Device_Code.equals("Sensor")||Device_Code.equals("sensor")){
                        Type="SS"+code;
                        Toast.makeText(this,"SS"+ Type, Toast.LENGTH_SHORT).show();
                    }else {
                        Type="ACT"+code;
                        Toast.makeText(this,"ACT"+Type, Toast.LENGTH_SHORT).show();
                        Log.i("ini tipenya",Type);
                    }
//                    int code = (int) ((new Date().getTime() / 1000L)%Integer.MIN_VALUE);
//                    int a = code - 1582883000;
//                    int i = (int) ((Math.random() * 2) + 1);
//                    RandomCode=(Type);
//                    Log.i("",Mac_Addres);
                      RegisterDevice();
                } catch (JSONException e) {
                    e.printStackTrace();
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
       String Id=sharedPrefManager.getSPEmail();
        String d_mac =Mac_Addres;
        String d_code = Type;
        if (d_code.equals("")) {
            showSnackbar();
        } else if (d_mac.equals("")) {
            showSnackbar();
        } else {
            retrofit2.Call<ResponseBody> call = InitRetrofit.getInstance().getApi().Aktivasi_Device(d_mac,d_code);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()){
//                        loading.dismiss();
                        try {
                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                            if (jsonRESULTS.getString("msg").equals("Berhasil")){

                            } else {
                                Toast.makeText(AddDevice.this, "Gagal,Ulangi Kembali", Toast.LENGTH_SHORT).show();
                                String error_message = jsonRESULTS.getString("msg");
                                Toast.makeText(AddDevice.this, error_message, Toast.LENGTH_SHORT).show();
                                Log.d("Pesan",error_message);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(AddDevice.this, "Gagal,Ulangi Kembali", Toast.LENGTH_SHORT).show();
//                        loading.dismiss();
                    }
                }
                        @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(AddDevice.this, "Gagal,Ulangi Kembali", Toast.LENGTH_SHORT).show();
                    Log.d("responsenya", t.toString());
                    Toast.makeText(AddDevice.this,t.toString(),Toast.LENGTH_LONG).show();
                    try {
                        publishfalse();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (KeyManagementException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    loading.dismiss();
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
    public void publishtrue() throws InterruptedException, NoSuchAlgorithmException, KeyManagementException, TimeoutException, URISyntaxException, IOException {
        String pesan="true";
        setupConnectionFactory();
        publish(pesan);
}

    public void publishfalse() throws InterruptedException, NoSuchAlgorithmException, KeyManagementException, TimeoutException, URISyntaxException, IOException {
        String pesan="false";
        setupConnectionFactory();
        publish(pesan);
    }
    public void setupConnectionFactory() {
        try {
            factory.setAutomaticRecoveryEnabled(false);
            factory.setUri("amqp://" + user + ":" + pass + "@" + host);
            factory.setVirtualHost(vhost);
        } catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    }

    public  void  publish(String message) throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException, InterruptedException {
        String queue_name_publish ="mqtt-subscription-"+Mac_Addres+"qos0";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = factory.newConnection();
        Log.d("ConnectionRMQ", "publish: "+connection.isOpen());
        Channel channel = connection.createChannel();
        Log.d("ChannelRMQ", "publish: "+channel.isOpen());
        String messageOn = message ;
        channel.basicPublish("", queue_name_publish,null,messageOn.getBytes());
    }
}
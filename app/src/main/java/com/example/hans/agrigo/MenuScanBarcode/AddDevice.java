package com.example.hans.agrigo.MenuScanBarcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hans.agrigo.Menu.MenuUtama;
import com.example.hans.agrigo.MenuLogin.Login;
import com.example.hans.agrigo.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDevice extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.scan)
    Button ScanBarcode;
    @BindView(R.id.adddevice)
    Button Tambahdevice;
    @BindView(R.id.id_device)
    TextView IDDevice;


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
                Intent i=new Intent(AddDevice.this, MenuUtama.class);
                startActivity(i);
                finish();
                Toast.makeText(AddDevice.this, "Success Compare Your Device", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
            }else{
                // jika qrcode berisi data
                try{
                    // converting the data json
                    JSONObject object = new JSONObject(result.getContents());
                    // atur nilai ke textviews
                    IDDevice.setText(object.getString("IDDevice"));
//                    Toast.makeText(this, (object.getString("nama")), Toast.LENGTH_SHORT).show();

//                    textViewTinggi.setText(object.getString("tinggi"));
                }catch (JSONException e){
                    e.printStackTrace();
                    // jika format encoded tidak sesuai maka hasil
                    // ditampilkan ke toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                }
            }
        }else{
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

    public void goBackMenu(){
        startActivity(new Intent(AddDevice.this, Login.class));
        finish();
    }
}

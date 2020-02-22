package com.example.hans.agrigo.DaftarZona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hans.agrigo.Config.RMQ;
import com.example.hans.agrigo.DaftarZona.GlobalVariablee.GlobalVariable;
import com.example.hans.agrigo.DaftarZona.Helper.RabbitMq;
import com.example.hans.agrigo.R;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class SiramZona extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner1;
    Button Publish;
    GlobalVariable gb = new GlobalVariable();
    RabbitMq rmq = new RabbitMq();

    String[] times={"59","58","57","56","55","54","53","52","51","50","49","48","47","46","45","44","43","42","41","40","39",
            "38","37","36","35","34"
    ,"33","32","31","30","29","28","27","26","25","24","23","22","21","20","19","18","17","16","15","14",
            "13","12","11","10","9","8","7","6","5","4","3","2","1","0"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_siram_zona);

        Publish=(Button)findViewById(R.id.siram);
        Publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    rmq.setupConnectionFactory();
                    kirim();
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
            }
        });


        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,times);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(aa);

    }
    private void kirim() throws InterruptedException, NoSuchAlgorithmException, KeyManagementException, TimeoutException, URISyntaxException, IOException {
        String pesan = "1000#1000";
//        rmq.setupConnectionFactory();
        rmq.publish(pesan);
//        rmq.publish(getApplicationContext(), times[position]);
    }


    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), times[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }
}
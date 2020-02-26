package com.example.hans.agrigo.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hans.agrigo.Config.RMQ;
import com.example.hans.agrigo.MenuLogin.Login;
import com.example.hans.agrigo.R;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SearchFragment extends Fragment{
    SharedPreferences sharedPreferences;
    public  static String KEY_MAC = "macSensor" ;
    private ColorfulRingProgressView spv;
    private TextView tvPercent, valueKelembapan, status ;
    float Maxvalue  = 610 ;
    RMQ rmq = new RMQ();
    String routingKey ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_search, null);

//        super.onCreate(savedInstanceState);
//        rmq.setupConnectionFactory();
//        getmsg();
//        spv = root.findViewById(R.id.spv);
//        tvPercent = root.findViewById(R.id.tvPercent);
//        valueKelembapan = root.findViewById(R.id.valueKelembapan);
//        status = root.findViewById( R.id.status );
////        sharedPreferences = this.getActivity().getSharedPreferences(Login.my_shared_preferences, Context.MODE_PRIVATE);
//        routingKey = sharedPreferences.getString(KEY_MAC, null);


        return root;
//        textSwitcher.setText(row[stringIndex]);
    }

    private void getmsg() {
        final Handler incomingMessageHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String title = "Pengumuman";
                String message = msg.getData().getString("msg");
                Log.d("RMQMessage", message);
                //nampilin message dari rabbit ke switcher

                //Konversi nilai kelembapan dari String ke float
                float kelembapan = Float.parseFloat(message);
                //Mengubah nilai kelembapan ke bentuk % (persen),(tipe data double)
                double convertToPercent = round(((kelembapan -180) / Maxvalue) * 100 , 2) ;
                //menampilkan hasil persentasi
                tvPercent.setText(String.valueOf(convertToPercent) + "%");
                //membagi nilai kelembapan  dengan Max Value
                float k = (kelembapan - 180) / Maxvalue;
                float b = (kelembapan - 180);
                //menampilkan hasil persentase ke dalam animasi grafik
                spv.setPercent(k * 100);
                //menampilkan hasil murni data kelembapan
                valueKelembapan.setText(" "+b);

            }
        };

        Thread subscribeThread = new Thread();
//        //ini gua coba iseng kak
        rmq.subscribe(incomingMessageHandler,subscribeThread);

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}

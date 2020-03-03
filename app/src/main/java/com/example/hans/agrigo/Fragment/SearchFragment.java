package com.example.hans.agrigo.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hans.agrigo.Config.AdapterDevice;
import com.example.hans.agrigo.Config.Item_Device;
import com.example.hans.agrigo.Config.Response_Device;
import com.example.hans.agrigo.Network.InitRetrofit;
import com.example.hans.agrigo.Network.NetworkService;
import com.example.hans.agrigo.R;
import com.example.hans.agrigo.Storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment{
    private RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_search, null);
        sharedPrefManager = new SharedPrefManager(getActivity());
        recyclerView =(RecyclerView)root.findViewById(R.id.list_menu);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(getActivity(),1);
        tampildevice();
        return root;
    }

    private void tampildevice() {
        String d_mail=sharedPrefManager.getSPEmail();
        NetworkService api = InitRetrofit.getInstance().getApi();
        Call<Response_Device> menuCall = api.Tampil_Device(d_mail);
        menuCall.enqueue(new Callback<Response_Device>() {
            @Override
            public void onResponse(Call<Response_Device> call, Response<Response_Device> response) {
                if (response.isSuccessful()){
                    Log.d("response api", response.body().toString());
                    List<Item_Device>data_menu= response.body().getMenu();
                    boolean status = response.body().isStatus();
                    if (status){
                        AdapterDevice adapter = new AdapterDevice(getActivity(),data_menu);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getActivity(), "Tidak Ada data Menu saat ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Response_Device> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getActivity(), "Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("pesan",t.getMessage());

            }
        });



    }


}

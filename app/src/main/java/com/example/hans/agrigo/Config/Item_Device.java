package com.example.hans.agrigo.Config;

import com.google.gson.annotations.SerializedName;

public class Item_Device {

    @SerializedName("guid")
    private String mac;

    @SerializedName("email")
    private String nama;


    public void setMac(String mac){

        this.mac = mac;
    }

    public String getMac(){
        return mac;
    }



    public void setNama(String nama){

        this.nama = nama;
    }

    public String getNama(){
        return nama;
    }
    @Override
    public String toString(){
        return
                "Item_Device{" +
                        "Mac= '" + mac+ '\'' +
                        "nama= '" + nama+ '\'' +
                        "}";
    }

}

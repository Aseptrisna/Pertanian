package com.example.hans.agrigo.DaftarZona.GlobalVariablee;

import android.content.SharedPreferences;

public class GlobalVariable {
    SharedPreferences pref;
    String prov,guid,tlp;
    String routingKey = "2c:f4:32:3c:61:dc";
    String queue_name_publish ="mqtt-subscription-"+routingKey+"qos0";
//
//    /**
//     * Get value provinsi yg telah register
//     * @param context
//     * @return value
//     */
//    public String getProv(Context context){
//        pref = context.getSharedPreferences("myToken",Context.MODE_PRIVATE);
//        prov="32";
//        return prov;
//    }
//
//    /**
//     * Get Value GUID saat register
//     * @param context
//     * @return
//     */
//    public String getGUID(Context context){
//        pref =context.getSharedPreferences("myToken",Context.MODE_PRIVATE);
//        guid=pref.getString(String.valueOf(R.string.pref_guid),null);
//        return guid;
//    }
//
//    /**
//     * Get Value GUID saat register
//     * @param context
//     * @return
//     */
//    public String getSeluler(Context context){
//        pref = context.getSharedPreferences("myToken",Context.MODE_PRIVATE);
//        tlp = pref.getString(String.valueOf(R.string.pref_tlp),null);
//        return tlp;
//    }

//    /**
//     * get Value Hosting FTP
//     * @return
//     */
//    public String getHostFTP(){
//        String ftp="siwasluimg-ftp.pptik.id";
//        return ftp;
//    }
//
//    /**
//     * get Value Username FTP
//     * @return
//     */
//    public String getUserFTP(){
//        String user ="siwaslu_publik";
//        return user;
//    }
//
//
//    /**
//     * get Value Password FTP
//     * @return
//     */
//    public String getPwFTP(){
//        String pw ="publik123!";
//        return pw;
//    }
//

    /**
     * get Value Queue RMQ
     * @return
     */
    public String queueReport(){
        String queue =queue_name_publish;
        return queue;
    }

    /**
     * get Value Username RMQ
     * @return
     */
    public String userQueue(){
        String userQueue="iot_pertanian";
        return userQueue;
    }


    /**
     * get Value password RMQ
     * @return
     */
    public String passQueue(){
        String passQueue="iotpertanian";
        return passQueue;
    }

    /**
     * get Value Hostname RMQ
     * @return
     */
    public String host(){
        String hostname ="167.205.7.226";
//                "rmq2.pptik.id";
        return hostname;
    }

    /**
     * get Value Virtual Host RMQ
     * @return
     */

    public String vhostRep(){
        String vhost="/iotpertanian";
        return vhost;
    }


    /**
     * get Value Exchange RMQ
     * @return
     */
    public String exchange(){
        String exchange ="";
        return exchange;
    }

//    public String apiProv(){
//        String prov ="daerah/get?level=1";
//        return prov;
//    }
//    public String hostAPI(){
//        String api = "http://167.205.7.18:5006/";
//        return api;
//    }
//
//    public String apiKab(){
//        String kab ="daerah/get?level=2&kode_pro=";
//        return kab;
//    }
//
//    public String apiKec(String kode_prov, String kode_kab){
//        String kec ="daerah/get?level=3&kode_pro="+kode_prov+"&kode_kab="+kode_kab;
//        return kec;
//    }
//
//    public String apiKel(String kode_prov,String kode_kab,String kode_kec){
//        String kel ="daerah/get?level=4&kode_pro="+kode_prov+"&kode_kab="+kode_kab+"&kode_kec="+kode_kec;
//        return kel;
//    }
}


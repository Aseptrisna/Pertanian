package com.example.hans.agrigo.MenuLogin.Model;

import com.google.gson.annotations.SerializedName;

public class LoginRespon {

    @SerializedName("rc")
    private String mRc;
    @SerializedName("result")
    private Result mResult;
    @SerializedName("rm")
    private String mRm;
    @SerializedName("success")
    private Boolean mSuccess;
    @SerializedName("message")
    private String message;
    @SerializedName("name")
    private String name;
    @SerializedName("name2")
    private String name2;
    @SerializedName("macSensor")
    private String macSensor;


    public String getmRc() {
        return mRc;
    }

    public void setmRc(String mRc) {
        this.mRc = mRc;
    }

    public Result getmResult() {
        return mResult;
    }

    public void setmResult(Result mResult) {
        this.mResult = mResult;
    }

    public String getmRm() {
        return mRm;
    }

    public void setmRm(String mRm) {
        this.mRm = mRm;
    }

    public Boolean getmSuccess() {
        return mSuccess;
    }

    public void setmSuccess(Boolean mSuccess) {
        this.mSuccess = mSuccess;
    }

    public boolean getSuccess() {return mSuccess; }
    public void  setSuccess(Boolean success) { mSuccess = success; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getMacSensor() {
        return macSensor;
    }

    public void setMacSensor(String macSensor) {
        this.macSensor = macSensor;
    }
}

package com.example.hans.agrigo.MenuRegister.Model;

import com.example.hans.agrigo.MenuLogin.Model.Result;
import com.google.gson.annotations.SerializedName;

public class RegisRespon {
    @SerializedName("rc")
    private String mRc;
    @SerializedName("result")
    private Result mResult;
    @SerializedName("rm")
    private String mRm;
    @SerializedName("success")
    private Boolean mSuccess;

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
}

package com.assignment.second.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class CommonProgressDailog {

    private ProgressDialog mProgressDailog;
    private Context mContext;
    private String msg;



   public CommonProgressDailog(Context context, String msg){
       mProgressDailog = new ProgressDialog(context);
       mProgressDailog.setMessage(msg);
       mProgressDailog.setCancelable(false);

    }

    public void showDailog(){
        mProgressDailog.show();
    }

    public void closeDailog(){
        mProgressDailog.dismiss();
    }

    public boolean isShowing(){

        return mProgressDailog.isShowing();
    }

    public void setProgress(int progress){
        mProgressDailog.setProgress(progress);
    }
}

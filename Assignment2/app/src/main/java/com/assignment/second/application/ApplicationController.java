package com.assignment.second.application;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static com.android.volley.VolleyLog.TAG;

public class ApplicationController extends Application {

    public static Context mContext;
    private static ApplicationController mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {

        super.onCreate();

        mContext = this;

        mInstance = this;
    }

    public static Context getContext() {

        return mContext;
    }

    public static ApplicationController getInstance() {

        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

}

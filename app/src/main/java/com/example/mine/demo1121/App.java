package com.example.mine.demo1121;

import android.app.Application;
import android.content.Context;


public class App extends Application
{

    private static Application mContext;
//    @Override
//    public Context getApplicationContext() {
//        return super.getApplicationContext();
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    public static Context getAppContext(){
        return mContext;
    }

}

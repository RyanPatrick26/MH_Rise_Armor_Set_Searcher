package com.ryanpatrick.mhrisearmorsetsearcher;

import android.annotation.SuppressLint;
import android.content.Context;

public class ApplicationSingleton {
    private Context context;
    @SuppressLint("StaticFieldLeak")
    private static ApplicationSingleton INSTANCE;

    public static synchronized ApplicationSingleton getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ApplicationSingleton();
        }
        return INSTANCE;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public Context getContext(){
        return context;
    }
}

package com.clfaa.androidnotes;

import android.app.Application;

import com.clfaa.androidnotes.utils.FileUtil;

/**
 * Created by changlifei on 16/5/11.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        FileUtil.onCreate(this);
    }
}

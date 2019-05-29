package com.saphal.getfit.models;

import android.app.Application;

import com.saphal.getfit.models.TypefaceUtil;

public class CustomFontApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/OpenSans-Regular.ttf");

    }
}
package com.saphal.getfit.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {

    private static final String CONTEXT_MAIN = "kathford";
    private Context context;
    private static SharedPreferenceHelper instance;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public static final String SHARE_KEY_FIRST_LAUNCH = "first_launch";

    private SharedPreferenceHelper() {
    }
    //create only one instance of SharedPreference to prevent multiple instances
    public static SharedPreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceHelper();
            preferences = context.getSharedPreferences(CONTEXT_MAIN, Context.MODE_PRIVATE);
            editor = preferences.edit();
        }
        return instance;
    }

    //first launch


    public boolean getFirstLaunch() {
        return preferences.getBoolean(SHARE_KEY_FIRST_LAUNCH, true);
    }

    public void setFirstLaunch(boolean isTrue) {
        editor.putBoolean(SHARE_KEY_FIRST_LAUNCH, isTrue);
        editor.apply();
    }



}

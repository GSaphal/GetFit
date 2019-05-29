package com.saphal.getfit;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.saphal.getfit.R;
import com.saphal.getfit.utils.SharedPreferenceHelper;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private SharedPreferenceHelper sharedPreferences;
    private TextView textview3;
    private TextView textview4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textview3=findViewById(R.id.textView3);
        textview4=findViewById(R.id.textView4);
        Typeface shadow = Typeface.createFromAsset(getAssets(), "fonts/Shadow.ttf");
        textview3.setTypeface(shadow );
        textview4.setTypeface(shadow );
        sharedPreferences = SharedPreferenceHelper.getInstance(getApplicationContext());
        loadScreen();
    }

    private void loadScreen() {
        Log.d(TAG, "loadScreen: ");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (sharedPreferences.getFirstLaunch()) {
                    sharedPreferences.setFirstLaunch(false);
                    //in order to disable back button going to splash Activity
                    Intent intent = new Intent(getApplicationContext(),
                            MainActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {

                    Intent intent = new Intent(getApplicationContext(),
                            MainActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

            }
        }, 4000);
    }
}
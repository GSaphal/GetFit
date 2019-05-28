package com.saphal.getfit;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }


    private void loadScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //move from one activity to another
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        },500);
    }
}

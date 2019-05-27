package com.saphal.getfit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText tv_email,tv_username,password,conpassword;
    private Button btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_email = findViewById(R.id.tv_email);
        tv_username = findViewById(R.id.tv_username);
        password = findViewById(R.id.password);
        conpassword= findViewById(R.id.conpassword);
    }


}

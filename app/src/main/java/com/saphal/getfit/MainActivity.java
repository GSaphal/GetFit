package com.saphal.getfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.saphal.getfit.menu.MenuActivity;

public class MainActivity extends AppCompatActivity {

    private EditText login_username,login_password;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);
    }
    public void onBtnSignUp(View view){

        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
    public void onBtnClickLogin(View view) {

        String user = login_username.getText().toString();
        String pass = login_password.getText().toString();


        if (validData(user, pass)){
            if (user.equals("user") && pass.equals("password")){

                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }else {

                Toast.makeText(this, "Username or password not correct", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validData(String user, String pass) {
        boolean isValid =true;
        if (user.isEmpty()){
            Toast.makeText(this, "Username cannot be blank", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        if (pass.isEmpty()){
            Toast.makeText(this, "Password cannot be emptu", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (pass.length() < 5){
            Toast.makeText(this, "Password must be more than 5 characters", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }
}

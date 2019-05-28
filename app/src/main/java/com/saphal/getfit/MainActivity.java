package com.saphal.getfit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.saphal.getfit.menu.MenuActivity;
import com.saphal.getfit.utils.FirebaseHelper;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText login_username, login_password;
    private Button btn_login;
    private FirebaseHelper mFirebaseHelper;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_username = (TextInputEditText)findViewById(R.id.login_username);
        login_password = (TextInputEditText)findViewById(R.id.login_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        mFirebaseHelper = new FirebaseHelper(getApplicationContext());

    }

    public void onBtnSignUp(View view) {

        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }

    public void onBtnClickLogin(View view) {

        String email = login_username.getText().toString();
        String password = login_password.getText().toString();
        validData(email,password);
        checkSignIn(email, password);


    }
    private boolean validData(String email, String password) {
        boolean isValid = true;
        if (email.isEmpty()) {
            Toast.makeText(this, "Email cannot be Empty", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (password.length() < 5) {
            Toast.makeText(this, "Password must be more than 5 characters", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }

    private void checkSignIn(String user, String pass) {
        mFirebaseHelper.getmAuth().signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mFirebaseHelper.getmAuth().getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
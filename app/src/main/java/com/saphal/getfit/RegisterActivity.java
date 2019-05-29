package com.saphal.getfit;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.saphal.getfit.utils.FirebaseHelper;

public class RegisterActivity extends AppCompatActivity {


    private TextInputEditText tv_email, tv_password, tv_conpassword;
    private Button btn_register;
    private FirebaseHelper mFirebaseHelper;
    private static final String TAG = "RegisterActivity";
    private boolean isValid=true;
    private TextView app_name;
    private TextView app_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_email =findViewById(R.id.tv_email);
        tv_password = findViewById(R.id.tv_password);
        tv_conpassword = findViewById(R.id.tv_conpassword);
        btn_register =  findViewById(R.id.btn_register);
        app_name=findViewById(R.id.app_name);
        app_desc=findViewById(R.id.app_desc);
        Typeface shadow = Typeface.createFromAsset(getAssets(), "fonts/Shadow.ttf");
        app_name.setTypeface(shadow);
        app_desc.setTypeface(shadow);
        mFirebaseHelper = new FirebaseHelper(getApplicationContext());
    }


    public void onBtnClickRegister(View view) {
        String email,password, conpassword;
        email = tv_email.getText().toString();

        password = tv_password.getText().toString();

        conpassword = tv_conpassword.getText().toString();
        validData(email, password, conpassword);
        if(isValid=true) {
            signUpUser(email, password);
        }
    }

    private boolean validData(String email, String password, String conpassword) {
//        if (HelperUtils.isValidEmail(email)){
//
//        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Email cannot be Empty", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (password.length() < 5) {
            Toast.makeText(this, "Password must be more than 5 characters", Toast.LENGTH_SHORT).show();
            isValid = false;
        } else if (!password.equals(conpassword)) {
            Toast.makeText(this, "Password and Confirmation password are different.", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        return isValid;
    }

    private void signUpUser(String email, String password) {
        mFirebaseHelper.getmAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(RegisterActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mFirebaseHelper.getmAuth().getCurrentUser();
                            updateUI(user);
                        } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this,task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                });

    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
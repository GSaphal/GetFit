package com.saphal.getfit.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.saphal.getfit.MainActivity;
import com.saphal.getfit.RegisterActivity;
import com.saphal.getfit.models.Register;

import static com.firebase.ui.auth.AuthUI.TAG;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class FirebaseHelper {
    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference mRef;

    public FirebaseHelper(Context context) {
        this.context = context;
        this.mAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
        this.mRef = database.getReference();
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public DatabaseReference getmRef() {
        return mRef;
    }
}

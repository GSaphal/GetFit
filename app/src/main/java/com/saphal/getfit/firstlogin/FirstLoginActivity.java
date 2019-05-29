package com.saphal.getfit.firstlogin;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.saphal.getfit.R;
import com.saphal.getfit.menu.MenuActivity;
import com.saphal.getfit.models.Upload;
import com.saphal.getfit.models.UserFire;
import com.saphal.getfit.utils.FirebaseHelper;
import com.squareup.picasso.Picasso;

public class FirstLoginActivity extends AppCompatActivity {
    private ImageView profile_img;
    public static final int PICK_IMAGE_REQUEST = 1;
    private Button upload;
    private Uri imageuri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private StorageTask mUploadTask;

    private TextView login_header, login_desc;
    private TextInputEditText tv_name, tv_age, tv_weight, tv_height, tv_goal_weight;
    private RadioGroup tv_gender, tv_active;
    private String value=null, keyId;
    private Spinner spinner1;
    private FirebaseHelper mFirebaseHelper;
    private String spinnertext;
    private String active=null;
    private ImageButton btn_select;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        login_header = findViewById(R.id.login_header);
        login_desc = findViewById(R.id.login_desc);
        Typeface shadow = Typeface.createFromAsset(getAssets(), "fonts/Shadow.ttf");
        login_header.setTypeface(shadow);
        login_desc.setTypeface(shadow);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        tv_gender = findViewById(R.id.tv_gender);
        spinner1=findViewById(R.id.spinner1);
        btn_select=findViewById(R.id.btn_select);
        tv_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_male:
                        value = "Male";
                        break;

                    case R.id.rb_female:
                        value = "Female";
                        break;
                }
            }
        });
        tv_weight = findViewById(R.id.tv_weight);
        tv_height = findViewById(R.id.tv_height);
        tv_goal_weight = findViewById(R.id.tv_goal_weight);
        tv_active = findViewById(R.id.tv_active);
        tv_active.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_ma:
                        active = "Moderately Active";
                        break;

                    case R.id.rb_a:
                        active = "Active";
                        break;
                    case R.id.rb_va:
                        active = "Very Active";
                        break;
                }
            }
        });
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Never Exercise", "Often in a Week", "Daily"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        spinnertext = spinner1.getSelectedItem().toString();


        profile_img = findViewById(R.id.profile_img);
        upload = findViewById(R.id.upload);
        mFirebaseHelper = new FirebaseHelper(getApplicationContext());
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

    }

    public void onBtnClickNext(View view) {
        String name, age, gender,weight,height,goal_weight;
        name = tv_name.getText().toString();
        age = tv_age.getText().toString();
        value = value.toString();
        weight=tv_weight.getText().toString();
        height=tv_height.getText().toString();
        goal_weight=tv_goal_weight.getText().toString();
        active=active.toString();
        spinnertext=spinner1.getSelectedItem().toString();



        keyId = mFirebaseHelper.getmRef().child("user").push().getKey();
        UserFire user = new UserFire(
                keyId,
                name,
                age,
                value,
                weight,
                height,
                goal_weight,
                active,
                spinnertext

        );

        addToDatabase(user);
    }


    private void addToDatabase(UserFire user) {
        mFirebaseHelper.getmRef().child(keyId)
                .child("user")
                .setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                Toast.makeText(FirstLoginActivity.this, "Your Profile is Successfully Saved.", Toast.LENGTH_SHORT).show();

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirstLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
        mFirebaseHelper.getmRef().child("user").child(keyId).removeValue();
    }

    public void onBtnUpload(View view) {
        if (mUploadTask != null && mUploadTask.isInProgress()) {
            Toast.makeText(this, "Upload in Progress", Toast.LENGTH_SHORT).show();

        } else {
            uploadFile();
        }
    }


    private void uploadFile() {
        if (imageuri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(imageuri));

            mUploadTask=fileReference.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Toast.makeText(FirstLoginActivity.this, "Upload successful", Toast.LENGTH_LONG).show();
                            Upload upload = new Upload(taskSnapshot.getStorage().getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(FirstLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(FirstLoginActivity.this, "Uploading", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null) {
            imageuri=data.getData();
            Picasso.with(this).load(imageuri).into(profile_img);

        }
    }

    public void onBtnSelect(View view) {
        openFileChooser();
        btn_select.setVisibility(View.GONE);
        profile_img.setVisibility(View.VISIBLE);
        upload.setVisibility(View.VISIBLE);

    }
}


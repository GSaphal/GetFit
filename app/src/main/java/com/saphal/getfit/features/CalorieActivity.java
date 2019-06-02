package com.saphal.getfit.features;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.saphal.getfit.firstlogin.FirstLoginActivity;
import com.saphal.getfit.menu.MenuActivity;
import com.saphal.getfit.models.CalorieFire;
import com.saphal.getfit.models.Upload;
import com.saphal.getfit.models.UserFire;
import com.saphal.getfit.utils.FirebaseHelper;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class CalorieActivity extends AppCompatActivity {
    public static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 10090;
    private ImageView image;
    private ImageButton btn_camera;
    private Toolbar activity_toolbar;
    private Spinner spinner2;
    private String spinnertext;
    private EditText foodname,calorie;
    private FirebaseHelper mFirebaseHelper;
    private String userID;
    private StorageTask mUploadTask;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    private Uri imageuri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_add);
        activity_toolbar = findViewById(R.id.activity_toolbar);
        activity_toolbar.setTitle("Add Food Item");
        foodname=findViewById(R.id.foodname);
        calorie=findViewById(R.id.calorie);
        btn_camera=findViewById(R.id.btn_camera);
        image=findViewById(R.id.image);
        spinner2 = findViewById(R.id.spinner2);
        Spinner dropdown = findViewById(R.id.spinner2);

        String[] items = new String[]{"Breakfast", "Lunch", "Dinner","Snack"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        spinnertext = spinner2.getSelectedItem().toString();
        mFirebaseHelper=new FirebaseHelper(getApplicationContext());
        mStorageRef = FirebaseStorage.getInstance().getReference("calorie");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("calorie");

    }

    public void onBtnCameraClick(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            image.setImageBitmap(bitmap);
            image.setVisibility(View.VISIBLE);
            imageuri= getImageUri(getApplicationContext(), bitmap);
            Toast.makeText(this, ""+imageuri, Toast.LENGTH_SHORT).show();
        }
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageuri = data.getData();
            Picasso.with(this).load(imageuri).into(image);
        }
    }
    private Uri getImageUri(Context applicationContext, Bitmap photo) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(CalorieActivity.this.getContentResolver(), photo, "calorie", null);
        return Uri.parse(path);

    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    public void onBtnCalorieSave(View view) {
        String food_name,calorie_active,spinner_text;
        food_name=foodname.getText().toString();
        calorie_active=calorie.getText().toString();
        spinner_text=spinnertext.toString();
        if (mFirebaseHelper.getmAuth().getCurrentUser() != null) {
            userID = mFirebaseHelper.getmAuth().getCurrentUser().getUid();
        };
        CalorieFire calorie = new CalorieFire(
                food_name,
                calorie_active,
                spinner_text

        );
        if (mUploadTask != null && mUploadTask.isInProgress()) {
            Toast.makeText(this, "Upload in Progress", Toast.LENGTH_SHORT).show();

        } else {
            uploadfile();
        }
        addToDatabase(calorie);


    }

    private void uploadfile() {
        if (imageuri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(imageuri));

            mUploadTask = fileReference.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Upload upload = new Upload(taskSnapshot.getStorage().getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                            Toast.makeText(CalorieActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CalorieActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(CalorieActivity.this, "Uploading", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void addToDatabase(CalorieFire calorie) {
        mFirebaseHelper.getmRef().child("calorie")
                .child(userID)
                .setValue(calorie)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        Toast.makeText(CalorieActivity.this, "Food Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CalorieActivity.this, "Error Adding Food", Toast.LENGTH_SHORT).show();
                    }
                });
        mFirebaseHelper.getmRef().child("calorie").child(userID).removeValue();
    }

        private String getFileExtension(Uri uri) {
            ContentResolver cR = getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(cR.getType(uri));
        }


    public void onBtnGalleryClick(View view) {
        openFileChooser();
    }
}


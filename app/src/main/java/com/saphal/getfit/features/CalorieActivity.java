package com.saphal.getfit.features;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.saphal.getfit.R;

public class CalorieActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1111;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie);
        image=findViewById(R.id.image);
    }


    public void onBtnCameraClick(View view) {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_REQUEST);
    }
    //type onactivityresult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==CAMERA_REQUEST){
            Bitmap bitmap=(Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
        }
    }
}

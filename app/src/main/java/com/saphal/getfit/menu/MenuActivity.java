package com.saphal.getfit.menu;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Constants;
import com.saphal.getfit.R;
import com.saphal.getfit.models.CalorieFragment;
import com.saphal.getfit.models.LandingFragment;
import com.saphal.getfit.models.UserFire;
import com.saphal.getfit.utils.FirebaseHelper;

//import de.hdodenhof.circleimageview.CircleImageView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG =null;
    private DrawerLayout drawer;
    private TextView app_name,fullname;
    private FirebaseHelper mFirebaseHelper;
   // private CircleImageView circle_view;
    private TextView email;
    DatabaseReference ref;
    private String keyId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mFirebaseHelper = new FirebaseHelper(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.Shadow);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new LandingFragment()).commit();
        }
        retrievedata();
        updateNavHeader();
    }

    private void retrievedata() {
        keyId= mFirebaseHelper.getmRef().child("user").push().getKey();
        ref=FirebaseDatabase.getInstance().getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateNavHeader() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        //circle_view = headerView.findViewById(R.id.circle_view);
        fullname = headerView.findViewById(R.id.fullname);
        email=headerView.findViewById(R.id.email);
        email.setText(mFirebaseHelper.getmAuth().getCurrentUser().getEmail());
       // fullname.setText(mFirebaseHelper.getmAuth().getCurrentUser().getDisplayName());
    //    Glide.with(this).load(mFirebaseHelper.getmAuth().getCurrentUser().getPhotoUrl()).into(circle_view);

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.calories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CalorieFragment()).commit();
                break;
//            case R.id.calories:
            //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CalorieFragment()).commit();
//                break;
//            case R.id.goals:
//                Toast.makeText(this, "Selected Goal Fragment", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Goals()).commit();
//                break;
//            case R.id.logout:
//                mFirebaseHelper.getmAuth().getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                break;
        }
            // for dyna    ((MenuActivity)getApplicationContext()).setTitleBar("My Calories");
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


//    public void setTitleBar(String title){
//        Toolbar toolbar = fid();
//        setsuppactionbar();
//                getsupptitlebar().settitle(title);
//    }

}


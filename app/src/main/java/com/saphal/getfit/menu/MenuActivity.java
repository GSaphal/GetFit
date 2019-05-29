package com.saphal.getfit.menu;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.saphal.getfit.MainActivity;
import com.saphal.getfit.R;
import com.saphal.getfit.RegisterActivity;
import com.saphal.getfit.models.Calorie;
import com.saphal.getfit.models.Goals;
import com.saphal.getfit.models.Logout;
import com.saphal.getfit.utils.FirebaseHelper;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private TextView app_name;
    private FirebaseHelper mFirebaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mFirebaseHelper = new FirebaseHelper(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.Shadow);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
        switch(item.getItemId())
        {
            case R.id.profile:
                Toast.makeText(this, "Profile Fragment Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.calories:
                Toast.makeText(this, "Selected Calorie Fragment", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Calorie()).commit();
                break;
            case R.id.goals:
                Toast.makeText(this, "Selected Goal Fragment", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Goals()).commit();
                break;
            case R.id.logout:
                mFirebaseHelper.getmAuth().getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;

        // for dyna    ((MenuActivity)getApplicationContext()).setTitleBar("My Calories");
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    public void setTitleBar(String title){
//        Toolbar toolbar = fid();
//        setsuppactionbar();
//                getsupptitlebar().settitle(title);
//    }

}

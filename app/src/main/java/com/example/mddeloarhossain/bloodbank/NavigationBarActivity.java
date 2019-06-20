package com.example.mddeloarhossain.bloodbank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class NavigationBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Navigation
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);


        //Navigation


        drawerLayout = findViewById(R.id.drawerId);
        NavigationView navigationView = findViewById(R.id.navigationId);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent intent;
        if(item.getItemId()==R.id.homeMenuId){
            intent = (new Intent(this, MainActivity.class));
            startActivity(intent);
        }
        if(item.getItemId()==R.id.profileMenuId){
            intent = (new Intent(this, DonorProfile.class));
            startActivity(intent);
        }
        return false;
    }
}

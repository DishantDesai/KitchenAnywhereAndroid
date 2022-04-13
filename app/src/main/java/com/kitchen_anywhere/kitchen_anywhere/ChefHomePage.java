package com.kitchen_anywhere.kitchen_anywhere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChefHomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    FloatingActionButton addDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_home_page);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        addDish = findViewById(R.id.add_dish);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ChefHomePage.this,Signup.class)
            }
        });
    }

    ChefHomePageFragment ChefHomePageFragment = new ChefHomePageFragment();
    ChefProfileFragment ChefProfileFragment = new ChefProfileFragment();
    ChefSettingsFragment ChefSettingsFragment = new ChefSettingsFragment();

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, ChefProfileFragment).commit();
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, ChefHomePageFragment).commit();
                return true;

            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, ChefSettingsFragment).commit();
                return true;
        }
        return false;
    }

}
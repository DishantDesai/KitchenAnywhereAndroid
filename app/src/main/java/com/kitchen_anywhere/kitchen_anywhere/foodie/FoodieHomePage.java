package com.kitchen_anywhere.kitchen_anywhere.foodie;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kitchen_anywhere.kitchen_anywhere.R;

public class FoodieHomePage extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_foodie_home_page);
        bottomNavigationView = findViewById(R.id.foodie_bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.foodie_home);
    }




    FoodieHomePageFragment FoodieHomePageFragment = new FoodieHomePageFragment();
    FoodieProfileFragment FoodieProfileFragment = new FoodieProfileFragment();
    FoodieSettingsFragment FoodieSettingsFragment = new FoodieSettingsFragment();

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.foodie_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.foodie_flFragment, FoodieProfileFragment).commit();
                return true;

            case R.id.foodie_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.foodie_flFragment, FoodieHomePageFragment).commit();
                return true;

            case R.id.foodie_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.foodie_flFragment, FoodieSettingsFragment).commit();
                return true;
        }
        return false;
    }

}
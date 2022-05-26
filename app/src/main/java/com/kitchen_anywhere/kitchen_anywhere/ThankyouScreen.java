package com.kitchen_anywhere.kitchen_anywhere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kitchen_anywhere.kitchen_anywhere.foodie.FoodieHomePage;

public class ThankyouScreen extends AppCompatActivity {
    TextView continueShopping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou);
        continueShopping = findViewById(R.id.continue_shopping);
        continueShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThankyouScreen.this, FoodieHomePage.class));
            }
        });
    }
}
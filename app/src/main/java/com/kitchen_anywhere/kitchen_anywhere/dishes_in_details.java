package com.kitchen_anywhere.kitchen_anywhere;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class dishes_in_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_in_details);


        Bundle bundle = this.getIntent().getExtras();
        System.out.println("-----------------------------------------------------------");
        System.out.println(bundle.getString("title"));
        String title = bundle.getString("title");
        String description = bundle.getString("description");
        String price = bundle.getString("price");
        String image = bundle.getString("image");




        TextView titleView = findViewById(R.id.txt_title);
        TextView descriptionView = findViewById(R.id.info_text);
        TextView priceView = findViewById(R.id.txt_price);
        ImageView pic = findViewById(R.id.img_title);


        Glide.with(this.getBaseContext())
                .load(image)
                .into(pic);


        titleView.setText(title);
        descriptionView.setText(description);
        priceView.setText(price);
    }
}
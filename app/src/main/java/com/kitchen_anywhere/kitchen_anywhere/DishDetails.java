package com.kitchen_anywhere.kitchen_anywhere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.io.Serializable;
import java.util.ArrayList;

public class DishDetails extends AppCompatActivity implements Serializable {
    FloatingActionButton incrementQtyBtn,decrementQtyBtn;
    Button addToCart;
    TextView countLabel;
    FoodModel dish;
    int qty;
    FoodModel filteredItem = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Call crete");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_in_details);
        Bundle bundle = this.getIntent().getExtras();
        incrementQtyBtn = findViewById(R.id.btn_increment);
        decrementQtyBtn = findViewById(R.id.btn_decrement);
        addToCart = findViewById(R.id.add_to_cart);
        countLabel = findViewById(R.id.txt_qty);
        dish = (FoodModel) getIntent().getSerializableExtra("dish");
//        int position = (int) getIntent().getSerializableExtra("position");
        for (int i=0;i<constant.cartItems.size();i++){
            if(constant.cartItems.get(i) != null && constant.cartItems.get(i).getdishImageLink().equalsIgnoreCase(dish.getdishImageLink())){
                filteredItem = constant.cartItems.get(i);
            }
        }
        if(constant.cartItems.size() > 0 && filteredItem != null){
            qty = filteredItem.getNumberInCart();
            countLabel.setText(String.valueOf(filteredItem.getNumberInCart()));
        }
        incrementQtyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qty < dish.getpending_limit()){
                    qty += 1;
                    countLabel.setText(String.valueOf(qty));
                }
            }
        });
        decrementQtyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qty > 1){
                    qty -= 1;
                    countLabel.setText(String.valueOf(qty));
                }

            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(constant.cartItems.size() > 0 && filteredItem != null){
                    constant.cartItems.get(constant.cartItems.size() - 1).setNumberInCart(qty);
                    Intent intent = new Intent(DishDetails.this, Cart.class);
                    startActivity(intent);
                }else if(qty > 0){
                    constant.cartItems.add(dish);
                    constant.cartItems.get(constant.cartItems.size() - 1).setNumberInCart(qty);
                    Intent intent = new Intent(DishDetails.this, Cart.class);
                    startActivity(intent);
                }
            }
        });
        TextView titleView = findViewById(R.id.txt_title);
        TextView descriptionView = findViewById(R.id.info_text);
        TextView priceView = findViewById(R.id.txt_price);
        ImageView pic = findViewById(R.id.img_title);

        Glide.with(this.getBaseContext())
                .load(dish.getdishImageLink())
                .into(pic);
        titleView.setText(dish.getdishTitle());
        descriptionView.setText(dish.getDescription());
        priceView.setText("$" + dish.getPrice().toString());
//        countLabel.setText(constant.cartItems.size() > 0 ? String.valueOf(constant.cartItems.get(position).getNumberInCart()): "0");
    }
    @Override
    public void onRestart(){

        super.onRestart();
        for (int i=0;i<constant.cartItems.size();i++){
            if(constant.cartItems.get(i) != null && constant.cartItems.get(i).getdishImageLink().equalsIgnoreCase(dish.getdishImageLink())){
                filteredItem = constant.cartItems.get(i);
            }
        }
        if(filteredItem != null){
            qty = filteredItem.getNumberInCart();
            countLabel.setText(String.valueOf(filteredItem.getNumberInCart()));
        }else{
            qty = 0;
            countLabel.setText("0");
        }
//        int position = (int) getIntent().getSerializableExtra("position");
//        updateCart(position);
    }
//    public void updateCart(int position){
//        System.out.println("position"+position);
//        countLabel.setText(constant.cartItems.size() > 0 ? String.valueOf(constant.cartItems.get(position).getNumberInCart()): "0");
//    }
}
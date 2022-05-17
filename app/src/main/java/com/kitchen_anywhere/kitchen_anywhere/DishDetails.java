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
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dishes_in_details);
        Bundle bundle = this.getIntent().getExtras();
        incrementQtyBtn = findViewById(R.id.btn_increment);
        decrementQtyBtn = findViewById(R.id.btn_decrement);
        addToCart = findViewById(R.id.add_to_cart);
        countLabel = findViewById(R.id.txt_qty);
        FoodModel dish = (FoodModel) getIntent().getSerializableExtra("dish");
        int position = (int) getIntent().getSerializableExtra("position");

        incrementQtyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cartSize = constant.cartItems.size();
                if(cartSize == 0){
                    constant.cartItems.add(dish);
                }
                int cartItemCount = constant.cartItems.get(position).getNumberInCart();
                if(cartItemCount < dish.getpending_limit()){
                    constant.cartItems.get(position).setNumberInCart(cartItemCount + 1);
                    countLabel.setText(String.valueOf(constant.cartItems.get(position).getNumberInCart()));
                }
            }
        });
        decrementQtyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(constant.cartItems.size() > 0){
                    int cartItemCount = constant.cartItems.get(position).getNumberInCart();
                    if(cartItemCount == 1){
                        constant.cartItems.get(position).setNumberInCart(0);
                        countLabel.setText(String.valueOf(constant.cartItems.get(position).getNumberInCart()));
                        constant.cartItems.remove(position);
                    }else if(cartItemCount >1){
                        constant.cartItems.get(position).setNumberInCart(cartItemCount - 1);
                        countLabel.setText(String.valueOf(constant.cartItems.get(position).getNumberInCart()));
                    }
                }

            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodModel cartItem = constant.cartItems.size() > 0 ? constant.cartItems.get(position) : null;
                if(cartItem != null){
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
        countLabel.setText(constant.cartItems.size() > 0 ? String.valueOf(constant.cartItems.get(position).getNumberInCart()): "0");
    }
    @Override
    public void onRestart(){
        super.onRestart();
        int position = (int) getIntent().getSerializableExtra("position");
        updateCart(position);
    }
    public void updateCart(int position){
        System.out.println("position"+position);
        countLabel.setText(constant.cartItems.size() > 0 ? String.valueOf(constant.cartItems.get(position).getNumberInCart()): "0");
    }
}
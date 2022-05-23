package com.kitchen_anywhere.kitchen_anywhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kitchen_anywhere.kitchen_anywhere.Interface.ChangeCartItem;
import com.kitchen_anywhere.kitchen_anywhere.adapter.CartAdapter;
import com.kitchen_anywhere.kitchen_anywhere.foodie.FoodieHomePage;
import com.kitchen_anywhere.kitchen_anywhere.foodie.ViewMoreDishes;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    TextView totalAmount,cartTotalAmt,taxAmt,startShopping;
    LinearLayout emptyCart;
    ScrollView cartItems;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_screen);
        initView();
        initList();
        CalculateCart();
    }
    private void initView() {
        recyclerViewList = findViewById(R.id.cartItemList);
        totalAmount = findViewById(R.id.totalAmt);
        cartTotalAmt = findViewById(R.id.cartTotalAmt);
        taxAmt = findViewById(R.id.taxAmt);
        cartItems = findViewById(R.id.cart_items);
        emptyCart = findViewById(R.id.empty_cart);
        startShopping = findViewById(R.id.start_shopping);
        startShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Cart.this, FoodieHomePage.class));
            }
        });
//        totalTxt = findViewById(R.id.totalTxt);
//        emptyTxt = findViewById(R.id.emptyTxt);

    }
    private void initList() {
        if(constant.cartItems.size() > 0){
            emptyCart.setVisibility(View.INVISIBLE);
            cartItems.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerViewList.setLayoutManager(linearLayoutManager);
            adapter = new CartAdapter((ArrayList<FoodModel>) constant.cartItems, this, new ChangeCartItem() {
                @Override
                public void changed() {
                    CalculateCart();
                }
            });
            recyclerViewList.setAdapter(adapter);
        }else{
            cartItems.setVisibility(View.INVISIBLE);
            emptyCart.setVisibility(View.VISIBLE);
        }
    }

    public Double getTotalFee() {
        ArrayList<FoodModel> listfood = (ArrayList<FoodModel>) constant.cartItems;
        double fee = 0;
        for (int i = 0; i < listfood.size(); i++) {
            fee = fee + (listfood.get(i).getPrice() * listfood.get(i).getNumberInCart());
        }
        return fee;
    }
    private void CalculateCart() {
        System.out.println("Call for update");
        if(constant.cartItems.size() == 0){
            cartItems.setVisibility(View.INVISIBLE);
            emptyCart.setVisibility(View.VISIBLE);
        }
        double percentTax = 0.02;
        tax = Math.round((getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((getTotalFee() + tax ) * 100) / 100;
        double itemTotal = Math.round(getTotalFee() * 100) / 100;

        cartTotalAmt.setText("$" + itemTotal);
        taxAmt.setText("$" + tax);
        totalAmount.setText("$" + total);
    }
}
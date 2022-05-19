package com.kitchen_anywhere.kitchen_anywhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kitchen_anywhere.kitchen_anywhere.Interface.ChangeCartItem;
import com.kitchen_anywhere.kitchen_anywhere.adapter.CartAdapter;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    TextView totalAmount,cartTotalAmt,taxAmt;
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
//        totalTxt = findViewById(R.id.totalTxt);
//        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
    }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter((ArrayList<FoodModel>) constant.cartItems, this, new ChangeCartItem() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
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
        double percentTax = 0.02;
        tax = Math.round((getTotalFee() * percentTax) * 100) / 100;
        double total = Math.round((getTotalFee() + tax ) * 100) / 100;
        double itemTotal = Math.round(getTotalFee() * 100) / 100;

        cartTotalAmt.setText("$" + itemTotal);
        taxAmt.setText("$" + tax);
        totalAmount.setText("$" + total);
    }
}
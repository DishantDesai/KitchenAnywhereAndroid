package com.kitchen_anywhere.kitchen_anywhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.util.ArrayList;

public class CartScreen extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    TextView totalAmount,cartTotalAmt,taxAmt;
    private double tax;
    private ScrollView scrollView;
//    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_screen);
        initView();
        initList();
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
        ArrayList<FoodModel> cartList = new ArrayList<>();
//        cartList.add()
//
//        recyclerViewList.setAdapter(adapter);
//        if (managementCart.getListCart().isEmpty()) {
//            emptyTxt.setVisibility(View.VISIBLE);
//            scrollView.setVisibility(View.GONE);
//        } else {
//            emptyTxt.setVisibility(View.GONE);
//            scrollView.setVisibility(View.VISIBLE);
//        }
    }
}
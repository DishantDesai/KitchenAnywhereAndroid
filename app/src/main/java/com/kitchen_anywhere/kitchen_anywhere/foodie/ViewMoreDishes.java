package com.kitchen_anywhere.kitchen_anywhere.foodie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.adapter.FoodAdapter;
import com.kitchen_anywhere.kitchen_anywhere.adapter.ViewMoreAdapter;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.ArrayList;

public class ViewMoreDishes extends AppCompatActivity {
    private ViewMoreAdapter dishAdapter;
    private RecyclerView  recyclerViewDishList;
    private EditText searchView;
    private LinearLayout noDataFound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_more_dishes);
        initView();
        getDish();
    }
    private void initView() {
        recyclerViewDishList = findViewById(R.id.view_more_recycle_view);
        searchView = findViewById(R.id.search_dish);
        noDataFound = findViewById(R.id.no_data_found);
        searchView.clearFocus();
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterList(""+charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void getDish() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewDishList.setLayoutManager(linearLayoutManager);
        if(constant.Matchdishdata.size() == 0) {
            dishAdapter = new ViewMoreAdapter((ArrayList<FoodModel>) constant.alldishdata, this);
        }
        else
        {
            dishAdapter = new ViewMoreAdapter((ArrayList<FoodModel>) constant.Matchdishdata, this);
        }
        recyclerViewDishList.setAdapter(dishAdapter);
    }
    private void filterList(String newText){
        ArrayList<FoodModel> filteredList = new ArrayList<>();
        for (FoodModel item: constant.alldishdata){
            if(item.getdishTitle().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            recyclerViewDishList.setVisibility(View.INVISIBLE);
            noDataFound.setVisibility(View.VISIBLE);
        }else{
            noDataFound.setVisibility(View.INVISIBLE);
            recyclerViewDishList.setVisibility(View.VISIBLE);
            dishAdapter.setFilteredList(filteredList);
        }

    }

}
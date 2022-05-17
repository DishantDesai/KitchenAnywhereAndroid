package com.kitchen_anywhere.kitchen_anywhere.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kitchen_anywhere.kitchen_anywhere.DishDetails;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.io.Serializable;
import java.util.ArrayList;
import com.kitchen_anywhere.kitchen_anywhere.R;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> implements Serializable {
    ArrayList<FoodModel> foodItems;
    private Activity context;
    public FoodAdapter (Activity context,ArrayList<FoodModel> foodItems) {
        this.foodItems = foodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        System.out.println("parent "+ parent);
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodie_home_listview, parent, false);
        return new ViewHolder (inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        holder.title.setText(foodItems.get(position).getdishTitle());
        holder.price.setText("$" + String.valueOf(foodItems.get(position).getPrice()));
        holder.description.setText(String.valueOf(foodItems.get(position).getDescription()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DishDetails.class);

                intent.putExtra("dish",foodItems.get(position));
                intent.putExtra("position",position);

                context.startActivity(intent);
            }
        });

        Glide.with(holder.itemView.getContext())
                .load(foodItems.get(position).getdishImageLink())
                .into(holder.pic);


    }
    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price,description;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.food_item_text_title);
            pic = itemView.findViewById(R.id.food_item_image);
            price = itemView.findViewById(R.id.price_text);
            description = itemView.findViewById(R.id.food_item_text_description);
        }
    }
}

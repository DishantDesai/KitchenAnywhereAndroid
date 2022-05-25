package com.kitchen_anywhere.kitchen_anywhere.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ChefOrderAdapter extends RecyclerView.Adapter<ChefOrderAdapter.ChefOrderViewHolder>{

    private ArrayList<FoodModel> foodItems;
    private Activity context;
    public ChefOrderAdapter (Activity context,ArrayList<FoodModel> foodItems) {
        this.foodItems = foodItems;
        this.context = context;
    }


    @NonNull
    @Override
    public ChefOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.chef_order_item, null);
        return new ChefOrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefOrderViewHolder holder, int position) {
        //getting the product of the specified position
        System.out.println(position);
        ArrayList<FoodModel> temp = foodItems;
        HashMap<String,Object> food = foodItems.get(position);
        //binding the data with the viewholder views
//        System.out.println(foodItems.get(position).toString());



        holder.txtTitle.setText(String.valueOf(food.get("dishTitle")));
        holder.txtQty.setText(String.valueOf(food.get("qty")));
        holder.txtPrice.setText(String.valueOf(food.get("price")));
        Glide.with(holder.itemView.getContext())
                .load(String.valueOf(food.get("dishImageLink")))
                .into(holder.img);
//        holder.img.setImageDrawable(this.context.getResources().getDrawable(food.getdishImageLink()));

    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

   public class ChefOrderViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtTitle,txtQty,txtPrice;


        public ChefOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.dishImageLink);
            txtTitle = itemView.findViewById(R.id.dishTitle);
            txtQty = itemView.findViewById(R.id.numberInCart);
            txtPrice = itemView.findViewById(R.id.price);
        }
    }

}





package com.kitchen_anywhere.kitchen_anywhere.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.dishes_in_details;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.util.ArrayList;


public class ChefFoodAdapter extends RecyclerView.Adapter<ChefFoodAdapter.ViewHolder> {
    ArrayList<FoodModel> foodItems;
    private Activity context;
    public ChefFoodAdapter(Activity context, ArrayList<FoodModel> foodItems) {
        this.foodItems = foodItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        System.out.println("parent "+ parent);
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.chef_home_listview, parent, false);
        return new ViewHolder (inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(foodItems.get(position).getTitle());
        holder.price.setText("$" + String.valueOf(foodItems.get(position).getPrice()));
        holder.description.setText(String.valueOf(foodItems.get(position).getDescription()));
//
//
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, dishes_in_details.class);
                context.startActivity(intent);
            }
        });
        Glide.with(holder.itemView.getContext())
                .load(foodItems.get(position).getImage())
                .into(holder.pic);


    }
    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price,description;
        ImageView pic;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.chef_item_text_title);
            pic = itemView.findViewById(R.id.chef_item_image);
            price = itemView.findViewById(R.id.chef_price_text);
            description = itemView.findViewById(R.id.chef_item_text_description);
        }
    }
}

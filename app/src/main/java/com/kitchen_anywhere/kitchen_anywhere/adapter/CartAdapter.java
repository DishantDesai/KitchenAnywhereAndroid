package com.kitchen_anywhere.kitchen_anywhere.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kitchen_anywhere.kitchen_anywhere.Interface.ChangeCartItem;
import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<FoodModel> cartItems;
    private ChangeCartItem changeCartItem;
    public CartAdapter (ArrayList<FoodModel> cartItems,Context context,ChangeCartItem changeCartItem) {
        this.cartItems = cartItems;
        this.changeCartItem = changeCartItem;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(cartItems.get(position).getdishTitle());
        holder.feeEachItem.setText("$" + String.valueOf(cartItems.get(position).getPrice()));
        holder.totalEachItem.setText("$" +String.valueOf(Math.round((cartItems.get(position).getQty() * cartItems.get(position).getPrice()) * 100) / 100));
        holder.num.setText(String.valueOf(cartItems.get(position).getQty()));
        Glide.with(holder.itemView.getContext())
                .load(cartItems.get(position).getdishImageLink())
                .into(holder.pic);
        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItems.get(position).setQty(cartItems.get(position).getQty() + 1);
                notifyDataSetChanged();
                changeCartItem.changed();
            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartItems.get(position).getQty() > 0){
                    cartItems.get(position).setQty(cartItems.get(position).getQty() - 1);
                    notifyDataSetChanged();
                    changeCartItem.changed();
                }
            }
        });
        holder.deleteCartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                constant.cartItems.remove(position);
                notifyDataSetChanged();
                changeCartItem.changed();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;
        ImageButton deleteCartItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.pic);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            deleteCartItem = itemView.findViewById(R.id.delete_cart_item);
        }
    }
}

package com.kitchen_anywhere.kitchen_anywhere.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;
import com.kitchen_anywhere.kitchen_anywhere.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class ChefOrderSectionAdapter extends RecyclerView.Adapter<ChefOrderSectionAdapter.ChefOrderSectionViewHolder>{

    ArrayList<OrderModel> orderItems;
    private Activity context;

    public ChefOrderSectionAdapter (Activity context,ArrayList<OrderModel> orderItems) {
        this.orderItems = orderItems;
        this.context = context;
        System.out.println("------call constructor");
    }

    public void updateOrder(ArrayList<OrderModel> orderItems){
        this.orderItems = orderItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChefOrderSectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.chef_order_section, null);
        System.out.println("------call Inflator");
        return new ChefOrderSectionAdapter.ChefOrderSectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChefOrderSectionViewHolder holder, int position) {
//getting the product of the specified position
        OrderModel order = orderItems.get(position);

        //binding the data with the viewholder views
        holder.txt_order_id.setText(order.getorderId());
        holder.txt_order_date.setText(order.getorderDate().toString());
        holder.nameOfFoodie.setText(order.getnameOfFoodie());
        holder.contactOfFoodie.setText(order.getcontactOfFoodie());
        holder.txt_order_status.setText(order.getorderStatus());

        ArrayList<FoodModel> foods = (ArrayList<FoodModel>) order.getdishList();

        ChefOrderAdapter chidRecycleAdepter = new ChefOrderAdapter(this.context,foods);
        System.out.println("------call onbindviewholder");
        holder.childRecyclerView.setAdapter(chidRecycleAdepter);

    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class ChefOrderSectionViewHolder extends RecyclerView.ViewHolder{

        TextView txt_order_id,txt_order_date,nameOfFoodie,contactOfFoodie,txt_order_status;
        RecyclerView childRecyclerView;

        public ChefOrderSectionViewHolder(@NonNull View itemView) {
            super(itemView);
            System.out.println("------call chef section view class");
            txt_order_id = itemView.findViewById(R.id.txt_order_id);
            txt_order_date = itemView.findViewById(R.id.txt_order_date);
            nameOfFoodie = itemView.findViewById(R.id.nameOfFoodie);
            contactOfFoodie = itemView.findViewById(R.id.contactOfFoodie);
            txt_order_status = itemView.findViewById(R.id.txt_order_status);

            childRecyclerView = itemView.findViewById(R.id.chefOrderChildRecycleView);

        }
    }

}

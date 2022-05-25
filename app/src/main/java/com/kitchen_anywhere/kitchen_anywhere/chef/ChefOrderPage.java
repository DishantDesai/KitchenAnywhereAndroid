package com.kitchen_anywhere.kitchen_anywhere.chef;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.adapter.ChefOrderAdapter;
import com.kitchen_anywhere.kitchen_anywhere.adapter.ChefOrderSectionAdapter;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;
import com.kitchen_anywhere.kitchen_anywhere.model.OrderModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ChefOrderPage extends AppCompatActivity {

    RecyclerView recyclerView;
    ChefOrderSectionAdapter chefOrderSectionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_order_page);

        getOrderData();

        System.out.println("-^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + constant.allorderdata.size());


        System.out.println("-^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^22222^^");

    }

    void getOrderData()
    {
        ArrayList<OrderModel> orderlist = new ArrayList<>();
        FirebaseFirestore.getInstance().collection("Order").whereEqualTo("chefId", constant.CurrentUser.getUserID()).get().

                addOnCompleteListener(
                        new OnCompleteListener<QuerySnapshot>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful())
                                {

                                    ArrayList<OrderModel> orderlist = new ArrayList<>();
                                    List<FoodModel> foodlist = new ArrayList<>();
                                    for(QueryDocumentSnapshot doc : task.getResult())
                                    {
                                        HashMap<String, String> map = new HashMap<String, String>();
                                        Timestamp t= (Timestamp) doc.getData().get("orderDate");
                                        Date d = t.toDate();
                                        foodlist = (List<FoodModel>) doc.getData().get("dishList");
                                        orderlist.add(new OrderModel(doc.getData().get("chefId").toString(),
                                                doc.getData().get("contactOfFoodie").toString(),foodlist,
                                                doc.getData().get("nameOfFoodie").toString(),
                                                d,
                                                doc.getData().get("orderId").toString(),
                                                doc.getData().get("orderStatus").toString(),
                                                doc.getData().get("userId").toString()

                                        ));
                                    }
                                    constant.allorderdata = orderlist;


                                    //getting the recyclerview from xml
                                    recyclerView = (RecyclerView) findViewById(R.id.recycle_chef_order);
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(ChefOrderPage.this));
//
//                                  //creating recyclerview adapter
                                    chefOrderSectionAdapter = new ChefOrderSectionAdapter(ChefOrderPage.this,constant.allorderdata);
                                    recyclerView.setAdapter(chefOrderSectionAdapter);
//                                    chefOrderSectionAdapter.updateOrder(constant.allorderdata);


                                }
                            }
                        }
                );
    }
}
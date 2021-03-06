package com.kitchen_anywhere.kitchen_anywhere.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.adapter.ChefOrderSectionAdapter;
import com.kitchen_anywhere.kitchen_anywhere.adapter.FoodAdapter;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;
import com.kitchen_anywhere.kitchen_anywhere.model.OrderModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FoodieOrderPage extends AppCompatActivity {
    RecyclerView recyclerView;
    private ChefOrderSectionAdapter chefOrderSectionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodie_order_page);
        getOrderData();

    }

    void getOrderData()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycle_foodie_order);
        ArrayList<OrderModel> orderlist = new ArrayList<>();
        FirebaseFirestore.getInstance().collection("Order").whereEqualTo("userId",constant.CurrentUser.getUserID()).get().

                addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {

                            List<OrderModel> orderlist = new ArrayList<>();
                            List<HashMap<String,Object>> foodlist = new ArrayList<>();
                            for(QueryDocumentSnapshot doc : task.getResult())
                            {
                                HashMap<String, String> map = new HashMap<String, String>();
                                Timestamp t= (Timestamp) doc.getData().get("orderDate");
                                Date d = t.toDate();

                                foodlist = (List<HashMap<String, Object>>) doc.getData().get("dishList");


                                List<FoodModel> allDish = new ArrayList<>();
                                for(int i=0;i<foodlist.size();i++)
                                {
                                    HashMap<String,Object> f = foodlist.get(i);

                                    allDish.add(
                                            new FoodModel(
                                                    f.get("id").toString(),
                                                    f.get("dishTitle").toString(),f.get("description").toString(),
                                                    f.get("typeOfDish").toString(),
                                                    Double.parseDouble(f.get("price").toString()),
                                                    f.get("dishImageLink").toString(),
                                                    3,
                                                    f.get("chef_id").toString(),
                                                    (List<String>) f.get("favouriteUserID"),
                                                    Integer.parseInt(f.get("categoryId").toString()),
                                                    Double.parseDouble(f.get("maxLimit").toString()),
                                                    Double.parseDouble(f.get("pending_limit").toString()),
                                                    (Boolean) f.get("isActive"),
                                                    (Boolean) f.get("isVegetarian"),
                                                    String.valueOf(f.get("postal_code"))
                                            )
                                    );

                                }
                                orderlist.add(new OrderModel(doc.getData().get("chefId").toString(),
                                        doc.getData().get("contactOfFoodie").toString(),allDish,
                                        doc.getData().get("nameOfFoodie").toString(),
                                        d,
                                        doc.getData().get("orderId").toString(),
                                        doc.getData().get("orderStatus").toString(),
                                        doc.getData().get("userId").toString()
                                ));
                                constant.allorderdata = new ArrayList<>();
                                constant.allorderdata.addAll(orderlist);
                            }
                            recyclerView.setLayoutManager(new LinearLayoutManager(FoodieOrderPage.this));
//
//                                  //creating recyclerview adapter
                            chefOrderSectionAdapter = new ChefOrderSectionAdapter(FoodieOrderPage.this,constant.allorderdata);
                            recyclerView.setAdapter(chefOrderSectionAdapter);
                        }
                    }
                }
        );
    }
}
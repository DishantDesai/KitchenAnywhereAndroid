package com.kitchen_anywhere.kitchen_anywhere.chef;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitchen_anywhere.kitchen_anywhere.AddDish;
import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.adapter.FoodAdapter;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.util.ArrayList;

public class ChefHomePageFragment extends Fragment {

    public ChefHomePageFragment() {
        // Required empty public constructor
    }
    private RecyclerView recyclerViewDishList;
    private RecyclerView.Adapter dishAdapter;
    ProgressBar progressBar;
    ListView d_list;
    FloatingActionButton addDish;
    public ListView listView ;

    public Handler h;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chef_home_page_fragment, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.loading);

        addDish = view.findViewById(R.id.add_dish);
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddDish.class);
                startActivity(intent);
            }
        });
        getDish(view);
        return view;
    }

    public void getDish(View view)
    {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerViewDishList =  view.findViewById(R.id.chef_dish_list_recycle_view);

        ArrayList<FoodModel> foodlist = new ArrayList<>();
        FirebaseFirestore.getInstance().collection("Dish")
                .get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>()
                {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {

                            ArrayList<FoodModel> foodlist = new ArrayList<>();
                            for(QueryDocumentSnapshot doc : task.getResult())
                            {
                                double mainPrice = Double.parseDouble(doc.getData().get("price").toString());

                                double maxLimit = Double.parseDouble(doc.getData().get("maxLimit").toString());

                                double pending_limit = Double.parseDouble(doc.getData().get("pending_limit").toString());

                                int categoryId = Integer.parseInt(doc.getData().get("categoryId").toString()) ;

                                foodlist.add(new FoodModel(  doc.getData().get("dishTitle").toString(),
                                        doc.getData().get("description").
                                                toString(),doc.getData().get("typeOfDish").toString(),
                                        mainPrice,
                                        doc.getData().get("dishImageLink").toString(),5,
                                        doc.getData().get("chef_id").toString(),
                                        new ArrayList<>(),categoryId,
                                                maxLimit,pending_limit,true,true,
                                                constant.CurrentUser.getPostal_code()
                                        )

                                );
                            }
                            constant.alldishdata = foodlist;
                            recyclerViewDishList.setLayoutManager(linearLayoutManager);
                            dishAdapter = new FoodAdapter(getActivity(), (ArrayList<FoodModel>) constant.alldishdata);
                            recyclerViewDishList.setAdapter(dishAdapter);
                            progressBar.setVisibility(View.INVISIBLE);
                            recyclerViewDishList.setVisibility(View.VISIBLE);
                        }
                    }
                }
        );


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
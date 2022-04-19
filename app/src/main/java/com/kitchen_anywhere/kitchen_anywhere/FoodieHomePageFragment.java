package com.kitchen_anywhere.kitchen_anywhere;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kitchen_anywhere.kitchen_anywhere.adapter.FoodAdapter;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.util.ArrayList;

public class FoodieHomePageFragment extends Fragment {
    ImageSlider imageSlider;
    private RecyclerView.Adapter dishAdapter;
    private RecyclerView  recyclerViewDishList;
    public FoodieHomePageFragment() {
        // Required empty public constructor
    }

     ProgressBar progressBar;
    ListView d_list;
    FloatingActionButton addDish;
    public ListView listView ;

    public Handler h;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_foodie_home_page_fragment, container, false);
//        progressBar = (ProgressBar)view.findViewById(R.id.foodie_loading);
//        d_list = (ListView) view.findViewById(R.id.foodie_DishList);
//        listView = view.findViewById(R.id.foodie_DishList);
        imageSlider = view.findViewById(R.id.image_slider);
//        progressBar.setVisibility(View.INVISIBLE);
        ArrayList<SlideModel> images =  new ArrayList<>();
        images.add(new SlideModel("https://www.wingsworldcuisine.ie/wp-content/uploads/2022/02/W2_Offer-Early-Bird-Offer-01012022.jpg",null));
        images.add(new SlideModel("https://www.wingsworldcuisine.ie/wp-content/uploads/2021/11/Wings-dubling-student-offer_19112021.jpg",null));
        images.add(new SlideModel("https://www.french-waterways.com/waterpress/wp-content/imagez/Copy-of-FW_French-dishes-1000x563.png",null));
        imageSlider.setImageList(images, ScaleTypes.FIT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                progressBar.setVisibility(View.INVISIBLE);

                ListView listView;
                String dishNames[] = {
                        "Paneer Tikka Masala",
                        "Paneer bhurji",
                        "Dhosa",
                        "Bhaji Pav",
                        "Paneer Tikka Masala",
                        "Paneer bhurji",
                        "Dhosa",
                        "Bhaji Pav",
                        "Paneer Tikka Masala",
                        "Paneer bhurji",
                        "Dhosa",
                        "Bhaji Pav"
                };

                String dishSubtitle[] = {
                        "spice panjabi dish",
                        "mid spice panjabi dish",
                        "south indian dish",
                        "indian dish",
                        "spice panjabi dish",
                        "mid spice panjabi dish",
                        "south indian dish",
                        "indian dish",
                        "spice panjabi dish",
                        "mid spice panjabi dish",
                        "south indian dish",
                        "indian dish"
                };

                String dishimageid[] = {
                        "https://www.thespruceeats.com/thmb/UMT0Jx65qwNd0wxGdPk8nED3FBo=/2000x1500/filters:fill(auto,1)/GettyImages-1042998066-518ca1d7f2804eb09039e9e42e91667c.jpg",
                        "https://www.eatthis.com/wp-content/uploads/sites/4/2019/06/deep-dish-pizza-chicago.jpg",
                        "https://insanelygoodrecipes.com/wp-content/uploads/2020/09/Indian-Dish-Malai-Kofta.png",
                        "https://us.123rf.com/450wm/fahrwasser/fahrwasser1710/fahrwasser171000119/87425544-fried-rice-with-vegetables-and-steamed-broccoli.jpg?ver=6"
                };

//                ListView dishList=(ListView)view.findViewById(R.id.foodie_DishList);
//
//                // For populating list data
//                FoodiecustomDistListAdapter foodList = new FoodiecustomDistListAdapter(getActivity(), dishNames, dishSubtitle, dishimageid);
//                dishList.setAdapter(foodList);



            }
        },4000);
        recyclerViewDish(view);
        return view;
    }

    private void recyclerViewDish(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerViewDishList =  view.findViewById(R.id.dish_list_recycle_view);
        recyclerViewDishList.setLayoutManager(linearLayoutManager);
        ArrayList<FoodModel> foodlist = new ArrayList<>();
        foodlist.add (new FoodModel(  "Pepperoni pizza","slices pepperoni mozzarella cheese, fresh oregano","Non-veg",13.0,"https://www.thespruceeats.com/thmb/UMT0Jx65qwNd0wxGdPk8nED3FBo=/2000x1500/filters:fill(auto,1)/GettyImages-1042998066-518ca1d7f2804eb09039e9e42e91667c.jpg",5));
        foodlist.add (new FoodModel(  "Pepperoni pizza","slices pepperoni mozzarella cheese, fresh oregano","Non-veg",13.0,"https://www.eatthis.com/wp-content/uploads/sites/4/2019/06/deep-dish-pizza-chicago.jpg",5));
        foodlist.add (new FoodModel(  "Pepperoni pizza","slices pepperoni mozzarella cheese, fresh oregano","Non-veg",13.0,"https://www.thespruceeats.com/thmb/UMT0Jx65qwNd0wxGdPk8nED3FBo=/2000x1500/filters:fill(auto,1)/GettyImages-1042998066-518ca1d7f2804eb09039e9e42e91667c.jpg",5));
        foodlist.add (new FoodModel(  "Pepperoni pizza","slices pepperoni mozzarella cheese, fresh oregano","Non-veg",13.0,"https://insanelygoodrecipes.com/wp-content/uploads/2020/09/Indian-Dish-Malai-Kofta.png",5));
        foodlist.add (new FoodModel(  "Pepperoni pizza","slices pepperoni mozzarella cheese, fresh oregano","Non-veg",13.0,"https://us.123rf.com/450wm/fahrwasser/fahrwasser1710/fahrwasser171000119/87425544-fried-rice-with-vegetables-and-steamed-broccoli.jpg?ver=6",5));
        System.out.println(foodlist);
        dishAdapter = new FoodAdapter(getActivity(),foodlist);
        recyclerViewDishList.setAdapter(dishAdapter);
    }
    public void getDish(View view)
    {


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
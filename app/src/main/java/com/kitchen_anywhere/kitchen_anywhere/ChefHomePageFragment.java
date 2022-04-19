package com.kitchen_anywhere.kitchen_anywhere;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kitchen_anywhere.kitchen_anywhere.adapter.customDistListAdapter;

public class ChefHomePageFragment extends Fragment {

    public ChefHomePageFragment() {
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

        View view = inflater.inflate(R.layout.fragment_chef_home_page_fragment, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.loading);
        d_list = (ListView) view.findViewById(R.id.DishList);
        listView = view.findViewById(R.id.DishList);
        addDish = view.findViewById(R.id.add_dish);
        addDish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),AddDish.class);
                startActivity(intent);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);

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

                ListView dishList=(ListView)view.findViewById(R.id.DishList);

                // For populating list data
                ChefcustomDistListAdapter customCountryList = new ChefcustomDistListAdapter(getActivity(), dishNames, dishSubtitle, dishimageid);
                dishList.setAdapter(customCountryList);



            }
        },4000);

        return view;
    }

    public void getDish(View view)
    {


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
package com.example.kitchenanywhereandroid;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class ChefHomePageFragment extends Fragment {

    public ChefHomePageFragment() {
        // Required empty public constructor
    }

     ProgressBar progressBar;
    ListView d_list;
    public ListView listView ;

    public Handler h;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chef_home_page_fragment, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.loading);
        d_list = (ListView) view.findViewById(R.id.DishList);
        listView = view.findViewById(R.id.DishList);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.INVISIBLE);

                ListView listView;
                String countryNames[] = {
                        "India",
                        "China",
                        "Nepal",
                        "Bhutan"
                };

                String capitalNames[] = {
                        "Delhi",
                        "Beijing",
                        "Kathmandu",
                        "Thimphu"
                };

                Integer imageid[] = {
                        R.drawable.splash_logo,
                        R.drawable.splash_logo,
                        R.drawable.splash_logo,
                        R.drawable.splash_logo

                };
//
//        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
//                "WebOS","Ubuntu","Windows7","Max OS X"};
//
//        listView = view.findViewById(R.id.DishList);
//
//        ArrayAdapter<String> adapter=new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1 ,mobileArray);
//        listView.setAdapter(adapter);
//
//        d_list.setVisibility(View.VISIBLE);



                ListView dishList=(ListView)view.findViewById(android.R.id.list);

                // For populating list data
                customDistListAdapter customCountryList = new customDistListAdapter(getActivity(), countryNames, capitalNames, imageid);
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
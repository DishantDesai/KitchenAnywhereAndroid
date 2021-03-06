package com.kitchen_anywhere.kitchen_anywhere.chef;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.adapter.settingListAdapter;
import com.kitchen_anywhere.kitchen_anywhere.Login;
import com.kitchen_anywhere.kitchen_anywhere.foodie.FoodieOrderPage;

public class ChefSettingsFragment extends Fragment {
    FirebaseAuth mAuth;
    public ChefSettingsFragment() {
        // Required empty public constructor
    }
    public ListView settingListView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chef_settings_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        settingListView = view.findViewById(R.id.settings);
        String settingOptions[] = {
                "Orders",
                "Your favourite",
                "Logout"
        };
        int settingOptionIcons[] = {
                R.drawable.ic_baseline_list_alt_24,
                R.drawable.ic_baseline_favorite_24,
                R.drawable.ic_baseline_exit_to_app_24
        };
        settingListAdapter settingOptionList = new settingListAdapter(getActivity(),settingOptions,settingOptionIcons);
        settingListView.setAdapter(settingOptionList);
        settingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(getActivity(), ChefOrderPage.class);
                        startActivity(intent);
                        break;
                    case 2:
                        logout(view);
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }
    public void logout(View view){
        mAuth.signOut();
        Intent intent = new Intent(getActivity(), Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
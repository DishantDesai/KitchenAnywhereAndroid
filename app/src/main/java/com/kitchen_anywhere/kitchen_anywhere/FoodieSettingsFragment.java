package com.kitchen_anywhere.kitchen_anywhere;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.kitchen_anywhere.kitchen_anywhere.adapter.settingListAdapter;

public class FoodieSettingsFragment extends Fragment {
    FirebaseAuth mAuth;
    public ListView settingListView ;

    public FoodieSettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_foodie_settings_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();
        settingListView = view.findViewById(R.id.foodie_settings);
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
        Intent intent = new Intent(getActivity(),login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
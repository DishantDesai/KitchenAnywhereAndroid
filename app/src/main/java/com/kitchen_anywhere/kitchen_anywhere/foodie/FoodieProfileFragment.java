package com.kitchen_anywhere.kitchen_anywhere.foodie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;

public class FoodieProfileFragment extends Fragment {

    public FoodieProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View row=inflater.inflate(R.layout.fragment_foodie_profile_fragment, container, false);

        TextView userName = (TextView) row.findViewById(R.id.foodie_userName);
        TextView EmailAddress = (TextView) row.findViewById(R.id.foodie_EmailAddress);
        TextView Phone = (TextView) row.findViewById(R.id.foodie_Phone);
        TextView postal_code = (TextView) row.findViewById(R.id.foodie_PostalAddress);
        TextView address = (TextView) row.findViewById(R.id.foodie_address);
        TextView userType = (TextView) row.findViewById(R.id.foodie_userType);

        userName.setText(constant.CurrentUser.getFullName());
        EmailAddress.setText(constant.CurrentUser.getEmail());
        Phone.setText(constant.CurrentUser.getPhoneNo());
        address.setText(constant.CurrentUser.getAddress());
        postal_code.setText(constant.CurrentUser.getPostal_code());
        userType.setText("User Type - Foodie");

        return row;
    }
}
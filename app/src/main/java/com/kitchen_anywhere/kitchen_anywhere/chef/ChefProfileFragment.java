package com.kitchen_anywhere.kitchen_anywhere.chef;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;

public class ChefProfileFragment extends Fragment {

    public ChefProfileFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View row = inflater.inflate(R.layout.fragment_chef_profile_fragment, container, false);
        TextView userName = (TextView) row.findViewById(R.id.chef_userName);
        TextView EmailAddress = (TextView) row.findViewById(R.id.chef_EmailAddress);
        TextView Phone = (TextView) row.findViewById(R.id.chef_Phone);
        TextView postal_code = (TextView) row.findViewById(R.id.chef_PostalAddress);
        TextView address = (TextView) row.findViewById(R.id.chef_address);
        TextView userType = (TextView) row.findViewById(R.id.chef_userType);

        userName.setText(constant.CurrentUser.getFullName());
        EmailAddress.setText(constant.CurrentUser.getEmail());
        Phone.setText(constant.CurrentUser.getPhoneNo());
        address.setText(constant.CurrentUser.getAddress());
        postal_code.setText(constant.CurrentUser.getPostal_code());
        userType.setText("User Type - Chef");

        return row;
    }
}
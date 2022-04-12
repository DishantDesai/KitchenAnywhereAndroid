package com.example.kitchenanywhereandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {
    TextInputLayout fullName,email,phoneNo,password,address,postalCode,confirmPassword;
    Button regBtn,regToLoginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullName = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        password= findViewById(R.id.et_password);
        confirmPassword= findViewById(R.id.confirm_password);
        phoneNo = findViewById(R.id.phone_number);
        address = findViewById(R.id.address);
        postalCode = findViewById(R.id.postal_code);
        regBtn = findViewById(R.id.register_btn);
        regToLoginBtn = findViewById(R.id.reg_to_login_btn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(view);
            }
        });


    }
    private Boolean isNameValid(){
        String name = fullName.getEditText().getText().toString();
        if(name.isEmpty()){
            fullName.setError("Field cannot be empty!");
            return false;
        }else{
            fullName.setError(null);
            return true;
        }
    }
    private Boolean isEmailValid(){
        String emailVal= email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(emailVal.isEmpty()){
            email.setError("Field cannot be empty!");
            return false;
        }else if(!emailVal.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }
    private Boolean isPhoneNoValid(){
        String phoneVal = phoneNo.getEditText().getText().toString();
        if(phoneVal.isEmpty()){
            phoneNo.setError("Field cannot be empty!");
            return false;
        }else{
            phoneNo.setError(null);
            return true;
        }
    }
    private Boolean isPasswordValid(){
        String passwordVal = password.getEditText().getText().toString();
        String passwordPattern = "^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[@#$%^&+=])" +
                "(?=\\S+$)" +
                ".{4,}" +
                "$";
        if(passwordVal.isEmpty()){
            password.setError("Field cannot be empty!");
            return false;
        }else if(!passwordVal.matches(passwordPattern)){
            password.setError("Password is too week");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }
    private Boolean isConfirmPasswordValid(){
        String passwordVal = password.getEditText().getText().toString();
        String confPasswordVal = confirmPassword.getEditText().getText().toString();
        if(confPasswordVal.isEmpty()){
            confirmPassword.setError("Field cannot be empty!");
            return false;
        }else if(!confPasswordVal.equals(passwordVal)){
            confirmPassword.setError("Confirm password must match password");
            return false;
        }else{
            confirmPassword.setError(null);
            return true;
        }
    }
    private Boolean isAddressValid(){
        String addressVal = address.getEditText().getText().toString();
        if(addressVal.isEmpty()){
            address.setError("Field cannot be empty!");
            return false;
        }else{
            address.setError(null);
            return true;
        }
    }
    private Boolean isPostalCodeValid(){
        String postalCodeVal = postalCode.getEditText().getText().toString();
        if(postalCodeVal.isEmpty()){
            postalCode.setError("Field cannot be empty!");
            return false;
        }else{
            postalCode.setError(null);
            return true;
        }
    }
    public void registerUser(View view){
        try {
            if(!isNameValid() | !isEmailValid() | !isPhoneNoValid() | !isPasswordValid() | !isConfirmPasswordValid() | !isAddressValid() | !isPostalCodeValid()){
                return;
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
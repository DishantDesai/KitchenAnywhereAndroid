package com.kitchen_anywhere.kitchen_anywhere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    public static final String TAG = "TAG";
    TextInputLayout fullName,email,phoneNo,password,address,postalCode,confirmPassword;
    RadioGroup userType;
    Button regBtn,regToLoginBtn;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
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
        userType = (RadioGroup) findViewById(R.id.userType);
        regBtn = findViewById(R.id.register_btn);
        regToLoginBtn = findViewById(R.id.reg_to_login_btn);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(view);
            }
        });

        findViewById(R.id.reg_to_login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
                finish();
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
    Boolean isEmailValid(){
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

        String fullnameVal = fullName.getEditText().getText().toString();
        String emailVal= email.getEditText().getText().toString();
        String passwordVal = password.getEditText().getText().toString();
        String addressVal = address.getEditText().getText().toString();
        String postalCodeVal = postalCode.getEditText().getText().toString();
        String phoneVal = phoneNo.getEditText().getText().toString();
        int userTypeIdVal = userType.getCheckedRadioButtonId();
        Boolean userTypeVal = ((RadioButton) findViewById(userTypeIdVal)).getText().toString().equals("Chef") ? true : false;
        try {
            if(!isNameValid() | !isEmailValid() | !isPhoneNoValid() | !isPasswordValid() | !isConfirmPasswordValid() | !isAddressValid() | !isPostalCodeValid()){
                return;
            }
            mAuth.createUserWithEmailAndPassword(emailVal,passwordVal).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection("User").document(userID);
                        Map<String,Object> user = new HashMap<>();
                        user.put("fullName",fullnameVal);
                        user.put("email",emailVal);
                        user.put("phoneNo",phoneVal);
                        user.put("address",addressVal);
                        user.put("postal_code",postalCodeVal);
                        user.put("isChef", userTypeVal);
                        user.put("userID",userID);
                        user.put("userStatus","pending");
                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d(TAG, "onFailure: " + e.toString());
                            }
                        });
                        Toast.makeText(Signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup.this, Login.class));
                    }else{
                        Toast.makeText(Signup.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
package com.kitchen_anywhere.kitchen_anywhere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button loginBtn;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        loginBtn=findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(view);
            }
        });

        findViewById(R.id.signupBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void loginUser(View view){

        String emailAddress= email.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(TextUtils.isEmpty(password) || TextUtils.isEmpty(emailAddress)) {

            if (TextUtils.isEmpty(password)) {
                pass.setError("Please enter Password");
                pass.requestFocus();
            }

            if (TextUtils.isEmpty(emailAddress)) {
                email.setError("Please enter User Name or Email");
                email.requestFocus();
            }
        }else
        {
            mAuth.signInWithEmailAndPassword(emailAddress,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(login.this, ChefHomePage.class));
                        startActivity(new Intent(login.this, FoodieHomePage.class));
                        finish();
                    }else{
                        Toast.makeText(login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
package com.example.kitchenanywhereandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn=findViewById(R.id.login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = findViewById(R.id.editTextTextPersonName);
                pass = findViewById(R.id.editTextTextPassword2);

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

                    Intent intent = new Intent(login.this,ChefHomePage.class);
                    startActivity(intent);
                    finish();
                }


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
}
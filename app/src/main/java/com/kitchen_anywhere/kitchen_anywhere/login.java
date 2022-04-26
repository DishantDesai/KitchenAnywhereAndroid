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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitchen_anywhere.kitchen_anywhere.model.UserModel;

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

                        FirebaseUser user = mAuth.getCurrentUser();


                        String userId=user.getUid();

                        FirebaseFirestore.getInstance().collection("User").whereEqualTo("userID",userId)
                                .get().addOnCompleteListener(
                                new OnCompleteListener<QuerySnapshot>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful())
                                        {
                                            for(QueryDocumentSnapshot doc : task.getResult())
                                            {
//                                            if(doc.getData())
                                                System.out.println(doc.getData());


                                                constant.CurrentUser  =  new UserModel(doc.getData().get("id").toString(),
                                                        doc.getData().get("email").toString(),
                                                        doc.getData().get("fullName").toString(),
                                                        doc.getData().get("address").toString(),
                                                        doc.getData().get("postal_code").toString(),
                                                        doc.getData().get("phoneNo").toString(),
                                                        (Boolean) doc.getData().get("isChef")
                                                );

                                                System.out.println(constant.CurrentUser);
                                            }
                                            if(constant.CurrentUser.getIsChef())
                                            {
                                                startActivity(new Intent(login.this, ChefHomePage.class));
                                            }
                                            else
                                            {
                                                startActivity(new Intent(login.this, FoodieHomePage.class));
                                            }
                                            Toast.makeText(login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }
                                }
                        );





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
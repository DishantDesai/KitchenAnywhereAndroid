package com.kitchen_anywhere.kitchen_anywhere;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;
import com.kitchen_anywhere.kitchen_anywhere.model.UserModel;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference rootRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser user = mAuth.getCurrentUser();

                if (user == null){
                    startActivity(new Intent(SplashScreenActivity.this, login.class));

                }else{
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
                                            startActivity(new Intent(SplashScreenActivity.this, ChefHomePage.class));
                                        }
                                        else
                                        {
                                            startActivity(new Intent(SplashScreenActivity.this, FoodieHomePage.class));
                                        }
                                        finish();
                                    }
                                }
                            }
                    );





                }


            }
        },4000);
    }

}
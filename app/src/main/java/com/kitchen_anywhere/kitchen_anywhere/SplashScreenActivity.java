package com.kitchen_anywhere.kitchen_anywhere;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;

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



//                    String userId=user.getUid();
//
//                    // Database reference pointing to root of database
//                    rootRef = FirebaseDatabase.getInstance().getReference();
//
//                    // Database reference pointing to demo node
//                    demoRef = rootRef.child("User");
//                    Query query = FirebaseDatabase.getInstance().getReference("User").equalTo(userId,"userID");
//                    demoRef.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            startActivity(new Intent(SplashScreenActivity.this, ChefHomePage.class));
//                            for(DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
////                            YourModel yourModel = childSnapshot.getValue(YourModel.class);
//
////                            if(yourModel.getDate().equalsIgnoreCase("2019/11/18")) {
////                                // Here is your desired location
////                            }
//                                System.out.println(childSnapshot);
//                                startActivity(new Intent(SplashScreenActivity.this, ChefHomePage.class));
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                        }
//                    });

                    startActivity(new Intent(SplashScreenActivity.this, FoodieHomePage.class));

                }
                finish();

            }
        },4000);
    }

}
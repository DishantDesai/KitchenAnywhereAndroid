package com.kitchen_anywhere.kitchen_anywhere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddDish extends AppCompatActivity {
    TextInputLayout title,addDishPrice,addDishcusineType,addDishDescription;
    RadioGroup addDish_diets,dish_activation;
    Button addDishImageSelection,addDishBtn;
    EditText  addDishQty;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dish);



        title = findViewById(R.id.addDishheading);
        addDishPrice = findViewById(R.id.addDishPrice);
        addDishcusineType= findViewById(R.id.addDishcusineType);
        dish_activation= (RadioGroup) findViewById(R.id.addDish_activation);
        addDishQty = findViewById(R.id.addDishQty);
        addDishDescription = findViewById(R.id.addDishDescription);
        addDish_diets = (RadioGroup) findViewById(R.id.addDish_diets);
        addDishImageSelection =  findViewById(R.id.addDishImageSelection);
        addDishBtn = findViewById(R.id.addDishBtn);

        findViewById(R.id.radio_btn_active).setActivated(true);


        addDishImageSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        addDishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("call");
                checkData();
            }
        });

    }
    void checkData()
    {
        String titleVal = title.getEditText().getText().toString();
        String addDishPriceVal = addDishPrice.getEditText().getText().toString();
        String addDishcusineTypeVal = addDishcusineType.getEditText().getText().toString();
        String description = addDishDescription.getEditText().getText().toString();
//        String addDishDescriptionVal = addDishDescription.getEditText().getText().toString();
        int dish_activationTemp = dish_activation.getCheckedRadioButtonId();
        System.out.println("id" + ((RadioButton) findViewById(dish_activationTemp)));
        Boolean dish_activationVal = ((RadioButton) findViewById(dish_activationTemp)).getText().toString().equals("Active") ? true : false;
        int addDish_dietsTemp = addDish_diets.getCheckedRadioButtonId();



        if(titleVal.isEmpty()){
            title.setError("Field cannot be empty!");
        }
        else if(addDishPriceVal.isEmpty()){
            addDishPrice.setError("Field cannot be empty!");
        }
        else if(addDishcusineTypeVal.isEmpty()){
            addDishcusineType.setError("Field cannot be empty!");
        }
        else if(description.isEmpty()){
            addDishDescription.setError("Field cannot be empty!");
        }
        else
        {

            firebaseDatabase = FirebaseDatabase.getInstance();

            databaseReference = firebaseDatabase.getReference("Dish");

            addDataToFirebase(new FoodModel( titleVal.toString(),
                    description.
                            toString(),addDishcusineTypeVal.toString(),
                    Double.parseDouble(addDishPriceVal),
                    "https://images.dailyhive.com/20210520142433/172093950_778326749492127_8446874264678364621_n.jpg",5,
                    constant.CurrentUser.getUserID(),
                    new ArrayList<>(),1,10.0,10.0,true,true
            ));
        }
        Boolean addDish_dietsVal = ((RadioButton) findViewById(addDish_dietsTemp)).getText().toString().equals("Active") ? true : false;

    }

    private void addDataToFirebase(FoodModel food) {
        databaseReference = firebaseDatabase.getReference("Dish");
        fStore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = fStore.collection("Dish").document();

        documentReference.set(food).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddDish.this, "data added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddDish.this, "Fail to add data " + e, Toast.LENGTH_SHORT).show();
            }
        });


//        firebaseDatabase.collection("cities").document("LA").set(city);
//
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                databaseReference.push().setValue(food);
//                Toast.makeText(AddDish.this, "data added", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(AddDish.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
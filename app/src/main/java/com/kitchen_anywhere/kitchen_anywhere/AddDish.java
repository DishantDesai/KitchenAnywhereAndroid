package com.kitchen_anywhere.kitchen_anywhere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AddDish extends AppCompatActivity {
    TextInputLayout title,addDishPrice,addDishcusineType,addDishDescription;
    RadioGroup addDish_diets,dish_activation;
    Button addDishImageSelection,addDishBtn;
    EditText  addDishQty;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageView addDishImageView;
    FirebaseStorage storage;
    StorageReference storageReference;
    private Uri filePath;

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

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



        addDishImageView = findViewById(R.id.addDishImageView);

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        addDishImageSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
                uploadImage();
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

    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }


    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                addDishImageView.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
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
                    new ArrayList<>(),1,10.0,10.0,true,true,
                    constant.CurrentUser.getPostal_code()
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

    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(AddDish.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(AddDish.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }
}
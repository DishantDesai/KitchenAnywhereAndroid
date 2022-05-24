package com.kitchen_anywhere.kitchen_anywhere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitchen_anywhere.kitchen_anywhere.chef.ChefHomePage;
import com.kitchen_anywhere.kitchen_anywhere.foodie.FoodieHomePage;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.model.UserModel;

import java.security.AuthProvider;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button loginBtn;
    private ImageView googleSignInBtn,fbSignInBtn;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        loginBtn=findViewById(R.id.login);
        googleSignInBtn = findViewById(R.id.google_signIn);
        fbSignInBtn = findViewById(R.id.facebook_signIn);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        //Google SingIn
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("404383085875-qibvqs0cctl4em1dbl8lcja53117gtc2.apps.googleusercontent.com")
                .requestEmail()
                .build();

        gsc = GoogleSignIn.getClient(this,gso);
        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });
        //Email-pass signIn
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(view);
            }
        });

        findViewById(R.id.signupBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
    void googleSignIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            Exception exception = task.getException();
            if(task.isSuccessful()){
                try {
                    GoogleSignInAccount account= task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_SHORT).show();
            }

        }

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
                        userRedirect();
                    }else{
                        Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential credentials = GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credentials).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    shouldAddUser();
                }else{
                    Toast.makeText(Login.this, "Login with google Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void shouldAddUser(){
        FirebaseUser user = mAuth.getCurrentUser();
        String userId=user.getUid();
        CollectionReference usersRef = fStore.collection("User");
        Query query = usersRef.whereEqualTo("userID", userId);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    userRedirect();
                }
                if(task.getResult().size() == 0 ){
                    addUserToFirebase();
                }
            }
        });

    }
    //Redirect user to appropriate screen based on User Type(Chef,Foodie)
    private void userRedirect(){
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
                                constant.CurrentUser  =  new UserModel(doc.getData().get("userID").toString(),
                                        doc.getData().get("email").toString(),
                                        doc.getData().get("fullName").toString(),
                                        doc.getData().get("address") != null ? doc.getData().get("address").toString() : "Montreal",
                                        doc.getData().get("postal_code") != null ? doc.getData().get("postal_code").toString(): "H1V1A8",
                                        doc.getData().get("phoneNo") != null ? doc.getData().get("phoneNo").toString(): "513-124-4142",
                                        (Boolean) doc.getData().get("isChef")
                                );

                            }
                            if(constant.CurrentUser.getIsChef())
                            {
                                startActivity(new Intent(Login.this, ChefHomePage.class));
                            }
                            else
                            {
                                startActivity(new Intent(Login.this, FoodieHomePage.class));
                            }
                            Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
        );
    }
    private void addUserToFirebase(){
        String userID = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("User").document(userID);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);;
        Map<String,Object> user = new HashMap<>();
        user.put("fullName",acct.getDisplayName());
        user.put("email",acct.getEmail());
        user.put("phoneNo",null);
        user.put("address",null);
        user.put("postal_code",null);
        user.put("isChef", false);
        user.put("userID",userID);
        user.put("userStatus","pending");
        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Login.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this, FoodieHomePage.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}
package com.example.hypertextassassins;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    String TAG="Hello";
    LottieAnimationView assassin;
    TextView logoheader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersCollectionRef = db.collection("Database");
        DocumentReference alovelaceDocumentRef = db.document("Database/Users");
        mAuth=FirebaseAuth.getInstance();
        Handler handler = new Handler();
        Intent Dashboard_intent = new Intent(this, dashboard.class);
        Intent Sign_in_intent=new Intent(this, LoginActivity.class);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        getSupportActionBar().hide();
        logoheader=findViewById(R.id.logoheader);
        assassin= findViewById(R.id.assassin);
        assassin.animate().setDuration(3000);
        fadee();

        db.collection("User").document("user2")
                .set(new User("gaja","Suraj","panya"))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });



        handler.postDelayed(new Runnable() {
            public void run() {
                if (currentUser!= null) {
                    Log.d("Hello", "Logged in");
                    startActivity(Dashboard_intent);
                    finish();
                } else {
                    Log.d("Hello", "Not Logged in");
                    startActivity(Sign_in_intent);
                    finish();
                }

            }
        }, 3000);

    }
    private void fadee()
    {   logoheader.animate().translationY(100).setDuration(3000);
        logoheader.setVisibility(View.VISIBLE);
        logoheader.postDelayed(new Runnable() {
            public void run() {
                logoheader.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }
}
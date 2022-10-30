package com.example.foodmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderFoodItems extends AppCompatActivity {

    private TextInputEditText foodNameEdt,foodPriceEdt, foodSuitedForEdt,foodImgEdt,foodLinkEdt,foodDescEdt;
    private Button addFoodBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String foodID;
    private Object FoodRVModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_items);
        foodNameEdt = findViewById(R.id.idEdtFoodName);
        foodPriceEdt = findViewById(R.id.idEdtFoodPrice);
        foodSuitedForEdt = findViewById(R.id.idEdtFoodSitedFor);
        foodImgEdt = findViewById(R.id.idEdtFoodImageLink);
        foodLinkEdt = findViewById(R.id.idEdtFoodImageLink);
        foodDescEdt = findViewById(R.id.idEdtFoodDescription);
        addFoodBtn = findViewById(R.id.idtBtnAddFood);
        loadingPB = findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Foods");

        addFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingPB.setVisibility(view.VISIBLE);
                String foodName = foodNameEdt.getText().toString();
                String foodPrice = foodPriceEdt.getText().toString();
                String suitedFor = foodSuitedForEdt.getText().toString();
                String foodImg = foodImgEdt.getText().toString();
                String foodLink = foodLinkEdt.getText().toString();
                String foodDesc = foodDescEdt.getText().toString();
                foodID = foodName;
                FoodRVModel cFoodRVModel = new FoodRVModel(foodName,foodDesc,foodPrice,suitedFor,foodImg,foodLink,foodID);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        loadingPB.setVisibility(View.GONE);
                        databaseReference.child(foodID).setValue(FoodRVModel);
                        Toast.makeText(OrderFoodItems.this, "Food Added..", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OrderFoodItems.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(OrderFoodItems.this, "Error is"+error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
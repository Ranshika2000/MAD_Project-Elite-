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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText foodNameEdt,foodPriceEdt, foodSuitedForEdt,foodImgEdt,foodLinkEdt,foodDescEdt;
    private Button updatedFoodBtn, deleteFoodBtn;
    private ProgressBar loadingPB;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String foodID;
    private FoodRVModel foodRVModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        foodNameEdt = findViewById(R.id.idEdtFoodName);
        foodPriceEdt = findViewById(R.id.idEdtFoodPrice);
        foodSuitedForEdt = findViewById(R.id.idEdtFoodSitedFor);
        foodImgEdt = findViewById(R.id.idEdtFoodImageLink);
        foodLinkEdt = findViewById(R.id.idEdtFoodImageLink);
        foodDescEdt = findViewById(R.id.idEdtFoodDescription);
        updatedFoodBtn = findViewById(R.id.idtBtnUpdateFood);
        deleteFoodBtn = findViewById(R.id.idtBtnDeleteFood);
        loadingPB = findViewById(R.id.idPBLoading);

        foodRVModel = getIntent().getParcelableExtra("Foods");
        if(foodRVModel!=null){
            foodNameEdt.setText(foodRVModel.getFoodName());
            foodPriceEdt.setText(foodRVModel.getFoodPrice());
            foodSuitedForEdt.setText(foodRVModel.getBestSuitedFor());
            foodImgEdt.setText(foodRVModel.getFoodImg());
            foodLinkEdt.setText(foodRVModel.getFoodLink());
            foodDescEdt.setText(foodRVModel.getFoodDescription());
            foodID = foodRVModel.getFoodID();

        }

        databaseReference = firebaseDatabase.getReference("Foods").child(foodID);
            updatedFoodBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    loadingPB.setVisibility(view.VISIBLE);
                    String foodName = foodNameEdt.getText().toString();
                    String foodPrice = foodPriceEdt.getText().toString();
                    String suitedFor = foodSuitedForEdt.getText().toString();
                    String foodImg = foodImgEdt.getText().toString();
                    String foodLink = foodLinkEdt.getText().toString();
                    String foodDesc = foodDescEdt.getText().toString();

                    Map<String,Object> map = new HashMap<>();
                    map.put("foodName" , foodName);
                    map.put("foodDescription" , foodDesc);
                    map.put("foodPrice" , foodPrice);
                    map.put("bestSuitedFor" , suitedFor);
                    map.put("foodImg" , foodImg);
                    map.put("FoodLink" , foodLink);
                    map.put("foodID" , foodID);

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            loadingPB.setVisibility(View.GONE);
                            databaseReference.updateChildren(map);
                            Toast.makeText(MainActivity.this, "Food Updated", Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });




                }
            });

        }
    }

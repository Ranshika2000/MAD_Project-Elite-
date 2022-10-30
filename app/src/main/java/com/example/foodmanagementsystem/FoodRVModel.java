package com.example.foodmanagementsystem;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodRVModel implements Parcelable {

    private String foodName;
    private  String foodDescription;
    private  String foodPrice;
    private  String bestSuitedFor;
    private  String foodImg;
    private  String FoodLink;
    private  String foodID;

    public FoodRVModel(String foodName, String foodDescription, String foodPrice, String bestSuitedFor, String foodImg, String foodLink, String foodID) {
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.bestSuitedFor = bestSuitedFor;
        this.foodImg = foodImg;
        FoodLink = foodLink;
        this.foodID = foodID;
    }

    protected FoodRVModel(Parcel in) {
        foodName = in.readString();
        foodDescription = in.readString();
        foodPrice = in.readString();
        bestSuitedFor = in.readString();
        foodImg = in.readString();
        FoodLink = in.readString();
        foodID = in.readString();
    }

    public static final Creator<FoodRVModel> CREATOR = new Creator<FoodRVModel>() {
        @Override
        public FoodRVModel createFromParcel(Parcel in) {
            return new FoodRVModel(in);
        }

        @Override
        public FoodRVModel[] newArray(int size) {
            return new FoodRVModel[size];
        }
    };

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getBestSuitedFor() {
        return bestSuitedFor;
    }

    public void setBestSuitedFor(String bestSuitedFor) {
        this.bestSuitedFor = bestSuitedFor;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public String getFoodLink() {
        return FoodLink;
    }

    public void setFoodLink(String foodLink) {
        FoodLink = foodLink;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public FoodRVModel(){


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foodName);
        parcel.writeString(foodDescription);
        parcel.writeString(foodPrice);
        parcel.writeString(bestSuitedFor);
        parcel.writeString(foodImg);
        parcel.writeString(FoodLink);
        parcel.writeString(foodID);
    }
}

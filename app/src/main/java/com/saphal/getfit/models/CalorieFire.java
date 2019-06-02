package com.saphal.getfit.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CalorieFire implements Parcelable {
    private String  foodname;
    private String  calorie;
    private String value;

    @Override
    public String toString() {
        return "CalorieFire{" +
                "foodname='" + foodname + '\'' +
                ", calorie='" + calorie + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    protected CalorieFire(Parcel in) {
        foodname = in.readString();
        calorie = in.readString();
        value = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foodname);
        dest.writeString(calorie);
        dest.writeString(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CalorieFire> CREATOR = new Creator<CalorieFire>() {
        @Override
        public CalorieFire createFromParcel(Parcel in) {
            return new CalorieFire(in);
        }

        @Override
        public CalorieFire[] newArray(int size) {
            return new CalorieFire[size];
        }
    };

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CalorieFire(String foodname, String calorie, String value) {
        this.foodname = foodname;
        this.calorie = calorie;
        this.value = value;
    }

    public CalorieFire() {
    }
}

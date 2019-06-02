package com.saphal.getfit.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NutritionFire implements Parcelable {



    private String diet_plan,brkfast,lunch1,snacks1,dinner1,totcal;

    public NutritionFire() {
    }

    protected NutritionFire(Parcel in) {
        diet_plan = in.readString();
        brkfast = in.readString();
        lunch1 = in.readString();
        snacks1 = in.readString();
        dinner1 = in.readString();
        totcal = in.readString();
    }

    public static final Creator<NutritionFire> CREATOR = new Creator<NutritionFire>() {
        @Override
        public NutritionFire createFromParcel(Parcel in) {
            return new NutritionFire(in);
        }

        @Override
        public NutritionFire[] newArray(int size) {
            return new NutritionFire[size];
        }
    };

    public String getDiet_plan() {
        return diet_plan;
    }

    public void setDiet_plan(String diet_plan) {
        this.diet_plan = diet_plan;
    }

    public String getBrkfast() {
        return brkfast;
    }

    public void setBrkfast(String brkfast) {
        this.brkfast = brkfast;
    }

    public String getLunch1() {
        return lunch1;
    }

    public void setLunch1(String lunch1) {
        this.lunch1 = lunch1;
    }

    public String getSnacks1() {
        return snacks1;
    }

    public void setSnacks1(String snacks1) {
        this.snacks1 = snacks1;
    }

    public String getDinner1() {
        return dinner1;
    }

    public void setDinner1(String dinner1) {
        this.dinner1 = dinner1;
    }

    public String getTotcal() {
        return totcal;
    }

    public void setTotcal(String totcal) {
        this.totcal = totcal;
    }

    public NutritionFire(String diet_plan, String brkfast, String lunch1, String snacks1, String dinner1, String totcal) {
        this.diet_plan = diet_plan;
        this.brkfast = brkfast;
        this.lunch1 = lunch1;
        this.snacks1 = snacks1;
        this.dinner1 = dinner1;
        this.totcal = totcal;
    }

    @Override
    public String toString() {
        return "NutritionFire{" +
                "diet_plan='" + diet_plan + '\'' +
                ", brkfast='" + brkfast + '\'' +
                ", lunch1='" + lunch1 + '\'' +
                ", snacks1='" + snacks1 + '\'' +
                ", dinner1='" + dinner1 + '\'' +
                ", totcal='" + totcal + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(diet_plan);
        dest.writeString(brkfast);
        dest.writeString(lunch1);
        dest.writeString(snacks1);
        dest.writeString(dinner1);
        dest.writeString(totcal);
    }
}

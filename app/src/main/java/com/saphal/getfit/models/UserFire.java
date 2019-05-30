package com.saphal.getfit.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.saphal.getfit.utils.FirebaseHelper;

public class UserFire implements Parcelable {
    public String tv_name;
    public String tv_age;
    public String value;
    public String tv_weight;
    public String tv_height;
    public String tv_goal_weight;
    public String active;
    public String spinner_text;


    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public String getTv_age() {
        return tv_age;
    }

    public void setTv_age(String tv_age) {
        this.tv_age = tv_age;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTv_weight() {
        return tv_weight;
    }

    public void setTv_weight(String tv_weight) {
        this.tv_weight = tv_weight;
    }

    public String getTv_height() {
        return tv_height;
    }

    public void setTv_height(String tv_height) {
        this.tv_height = tv_height;
    }

    public String getTv_goal_weight() {
        return tv_goal_weight;
    }

    public void setTv_goal_weight(String tv_goal_weight) {
        this.tv_goal_weight = tv_goal_weight;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getSpinner_text() {
        return spinner_text;
    }

    public void setSpinner_text(String spinner_text) {
        this.spinner_text = spinner_text;
    }

    public UserFire() {
    }

    public UserFire(String keyId, String tv_name, String tv_age, String value, String tv_weight, String tv_height, String tv_goal_weight, String active, String spinner_text) {
        this.tv_name = tv_name;
        this.tv_age = tv_age;
        this.value = value;
        this.tv_weight = tv_weight;
        this.tv_height = tv_height;
        this.tv_goal_weight = tv_goal_weight;
        this.active = active;
        this.spinner_text = spinner_text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}

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


    public UserFire() {
    }


    protected UserFire(Parcel in) {
        tv_name = in.readString();
        tv_age = in.readString();
        value = in.readString();
        tv_weight = in.readString();
        tv_height = in.readString();
        tv_goal_weight = in.readString();
        active = in.readString();
        spinner_text = in.readString();
    }

    public static final Creator<UserFire> CREATOR = new Creator<UserFire>() {
        @Override
        public UserFire createFromParcel(Parcel in) {
            return new UserFire(in);
        }

        @Override
        public UserFire[] newArray(int size) {
            return new UserFire[size];
        }
    };

    public String getTv_name() { return tv_name; }

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


    public UserFire(String tv_name, String tv_age, String value, String tv_weight, String tv_height, String tv_goal_weight, String active, String spinner_text) {
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
    public String toString() {
        return "UserFire{" +
                "tv_name='" + tv_name + '\'' +
                ", tv_age='" + tv_age + '\'' +
                ", value='" + value + '\'' +
                ", tv_weight='" + tv_weight + '\'' +
                ", tv_height='" + tv_height + '\'' +
                ", tv_goal_weight='" + tv_goal_weight + '\'' +
                ", active='" + active + '\'' +
                ", spinner_text='" + spinner_text + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tv_name);
        dest.writeString(tv_age);
        dest.writeString(value);
        dest.writeString(tv_weight);
        dest.writeString(tv_height);
        dest.writeString(tv_goal_weight);
        dest.writeString(active);
        dest.writeString(spinner_text);
    }
}

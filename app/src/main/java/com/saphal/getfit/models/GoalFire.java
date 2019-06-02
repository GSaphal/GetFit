package com.saphal.getfit.models;

import android.os.Parcel;
import android.os.Parcelable;

public class GoalFire implements Parcelable {
    private String calorie,step,goal_calorie,goal_step,time_req;

    public GoalFire(String calorie,String step, String goal_calorie, String goal_step, String time_req) {
        this.calorie=calorie;
        this.step = step;
        this.goal_calorie = goal_calorie;
        this.goal_step = goal_step;
        this.time_req = time_req;
    }

    public GoalFire() {
    }

    @Override
    public String toString() {
        return "GoalFire{" +
                "calorie='" + calorie + '\'' +
                ", step='" + step + '\'' +
                ", goal_calorie='" + goal_calorie + '\'' +
                ", goal_step='" + goal_step + '\'' +
                ", time_req='" + time_req + '\'' +
                '}';
    }

    protected GoalFire(Parcel in) {
        calorie = in.readString();
        step = in.readString();
        goal_calorie = in.readString();
        goal_step = in.readString();
        time_req = in.readString();
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public static final Creator<GoalFire> CREATOR = new Creator<GoalFire>() {
        @Override
        public GoalFire createFromParcel(Parcel in) {
            return new GoalFire(in);
        }

        @Override
        public GoalFire[] newArray(int size) {
            return new GoalFire[size];
        }
    };

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getGoal_calorie() {
        return goal_calorie;
    }

    public void setGoal_calorie(String goal_calorie) {
        this.goal_calorie = goal_calorie;
    }

    public String getGoal_step() {
        return goal_step;
    }

    public void setGoal_step(String goal_step) {
        this.goal_step = goal_step;
    }

    public String getTime_req() {
        return time_req;
    }

    public void setTime_req(String time_req) {
        this.time_req = time_req;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(calorie);
        dest.writeString(step);
        dest.writeString(goal_calorie);
        dest.writeString(goal_step);
        dest.writeString(time_req);
    }
}

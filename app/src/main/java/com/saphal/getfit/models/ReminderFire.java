package com.saphal.getfit.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ReminderFire implements Parcelable {

    private String remindnote;
    private String remindname;
    private String date_pick;

    protected ReminderFire(Parcel in) {
        remindnote = in.readString();
        remindname = in.readString();
        date_pick = in.readString();
        time_pick = in.readString();
    }

    public static final Creator<ReminderFire> CREATOR = new Creator<ReminderFire>() {
        @Override
        public ReminderFire createFromParcel(Parcel in) {
            return new ReminderFire(in);
        }

        @Override
        public ReminderFire[] newArray(int size) {
            return new ReminderFire[size];
        }
    };

    @Override
    public String toString() {
        return "ReminderFire{" +
                "remindnote='" + remindnote + '\'' +
                ", remindname='" + remindname + '\'' +
                ", date_pick='" + date_pick + '\'' +
                ", time_pick='" + time_pick + '\'' +
                '}';
    }

    public String getRemindnote() {
        return remindnote;
    }

    public void setRemindnote(String remindnote) {
        this.remindnote = remindnote;
    }

    public String getRemindname() {
        return remindname;
    }

    public void setRemindname(String remindname) {
        this.remindname = remindname;
    }

    public String getDate_pick() {
        return date_pick;
    }

    public void setDate_pick(String date_pick) {
        this.date_pick = date_pick;
    }

    public String getTime_pick() {
        return time_pick;
    }

    public void setTime_pick(String time_pick) {
        this.time_pick = time_pick;
    }

    public ReminderFire() {
    }

    public ReminderFire(String remindnote, String remindname, String date_pick, String time_pick) {
        this.remindnote = remindnote;
        this.remindname = remindname;
        this.date_pick = date_pick;
        this.time_pick = time_pick;
    }

    private String time_pick;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(remindnote);
        dest.writeString(remindname);
        dest.writeString(date_pick);
        dest.writeString(time_pick);
    }
}

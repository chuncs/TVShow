package com.udacity.classroom.yongchun.tvshow.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Popular implements Parcelable {

    private String name;
    private long id;
    private String poster_path;

    private Popular(Parcel in) {
        name = in.readString();
        id = in.readLong();
        poster_path = in.readString();
    }

    public static final Creator<Popular> CREATOR = new Creator<Popular>() {
        @Override
        public Popular createFromParcel(Parcel in) {
            return new Popular(in);
        }

        @Override
        public Popular[] newArray(int size) {
            return new Popular[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeLong(id);
        parcel.writeString(poster_path);
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getPosterPath() {
        return poster_path;
    }
}

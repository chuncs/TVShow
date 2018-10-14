package com.udacity.classroom.yongchun.tvshow.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Feed implements Parcelable {

    private int page;
    private int total_pages;
    private List<Popular> results;

    private Feed(Parcel in) {
        page = in.readInt();
        total_pages = in.readInt();
        results = in.createTypedArrayList(Popular.CREATOR);
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(page);
        parcel.writeInt(total_pages);
        parcel.writeTypedList(results);
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return total_pages;
    }

    public List<Popular> getResults() {
        return results;
    }
}

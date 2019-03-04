package com.iantje.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class PortalItem implements Parcelable {

    public String name;
    public String url;

    public PortalItem (String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    protected PortalItem(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<PortalItem> CREATOR = new Parcelable.Creator<PortalItem>() {
        @Override
        public PortalItem createFromParcel(Parcel source) {
            return new PortalItem(source);
        }

        @Override
        public PortalItem[] newArray(int size) {
            return new PortalItem[size];
        }
    };
}

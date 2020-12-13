package com.example.poms6;


import android.os.Parcel;
import android.os.Parcelable;

public class historyItem implements Parcelable {
    public String historyText;
    public historyItem(String text){
        this.historyText=text;
    }


    protected historyItem(Parcel in) {
        historyText = in.readString();
    }

    public static final Creator<historyItem> CREATOR = new Creator<historyItem>() {
        @Override
        public historyItem createFromParcel(Parcel in) {
            return new historyItem(in);
        }

        @Override
        public historyItem[] newArray(int size) {
            return new historyItem[size];
        }
    };

    public void setHistoryText(String text){
        historyText=text;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(historyText);
    }
}

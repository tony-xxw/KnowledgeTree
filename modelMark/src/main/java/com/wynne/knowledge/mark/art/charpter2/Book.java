package com.wynne.knowledge.mark.art.charpter2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * AIDL
 *
 * @author Wynne
 */
public class Book implements Parcelable {
    private String name;
    private int id;

    protected Book(Parcel in) {
        name = in.readString();
        id = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
    }
}

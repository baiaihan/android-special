package com.baijunyu.testaidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 95190 on 2018/8/22.
 */

public class Book implements Parcelable {

    public int bookid;
    public String bookName;

    public Book(int bookid, String bookName) {
        this.bookid = bookid;
        this.bookName = bookName;
    }


    protected Book(Parcel in) {
        bookid = in.readInt();
        bookName = in.readString();
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
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(bookid);
        parcel.writeString(bookName);
    }
}

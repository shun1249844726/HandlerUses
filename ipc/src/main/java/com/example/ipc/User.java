package com.example.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * hello
 *
 * @author xushun
 * @date 2018/1/31.
 */

public class User implements Parcelable {
    protected User(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}

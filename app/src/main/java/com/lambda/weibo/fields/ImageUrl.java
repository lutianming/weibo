package com.lambda.weibo.fields;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LU Tianming on 15-3-3.
 */
public class ImageUrl implements Parcelable{
    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    private String thumbnail_pic;
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}

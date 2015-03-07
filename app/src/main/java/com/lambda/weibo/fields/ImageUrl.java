package com.lambda.weibo.fields;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LU Tianming on 15-3-3.
 */
public class ImageUrl implements Parcelable{
    private static final String THUMBNAIL = "thumbnail";
    private static final String MIDDLE = "bmiddle";
    private static final String ORIGINAL = "large";

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }
    public String getMiddle_pic(){
        return thumbnail_pic.replace(THUMBNAIL, MIDDLE);
    }
    public String getLarge_pic(){
        return thumbnail_pic.replace(THUMBNAIL, ORIGINAL);
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

package com.lambda.weibo.fields;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LU Tianming on 15-3-3.
 */
public class AuthInfo implements Parcelable{
    private String access_token;
    private long uid;
    private String appkey;
    private String scope;
    private long create_at;
    private long expire_in;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(access_token);
        dest.writeLong(uid);
        dest.writeString(appkey);
        dest.writeLong(create_at);
        dest.writeLong(expire_in);

    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getCreate_at() {
        return create_at;
    }

    public void setCreate_at(long create_at) {
        this.create_at = create_at;
    }

    public long getExpire_in() {
        return expire_in;
    }

    public void setExpire_in(long expire_in) {
        this.expire_in = expire_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    private AuthInfo(Parcel in){
        access_token = in.readString();

        uid = in.readLong();
        appkey = in.readString();
        create_at = in.readLong();
        expire_in = in.readLong();

    }
    public static final Parcelable.Creator<AuthInfo> CREATOR
            = new Parcelable.Creator<AuthInfo>(){

        @Override
        public AuthInfo createFromParcel(Parcel source) {
            return new AuthInfo(source);
        }

        @Override
        public AuthInfo[] newArray(int size) {
            return new AuthInfo[size];
        }
    };
}

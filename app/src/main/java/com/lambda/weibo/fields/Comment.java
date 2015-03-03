package com.lambda.weibo.fields;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class Comment implements Parcelable{
    public static final String CREATED_AT = "created_at";
    public static final String ID = "id";
    public static final String TEXT = "text";
    public static final String SOURCE = "source";
    public static final String USER = "user";
    public static final String MID = "mid";
    public static final String IDSTR = "idstr";
    public static final String STATUS = "status";
    public static final String REPLY_COMMENT = "reply_comment";


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String created_at;
    private long id;
    private String text;
    private String source;
    private User user;
    private String mid;
    private String idstr;
    private Status status;
    private Comment reply_comment;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(created_at);
        dest.writeLong(id);
        dest.writeString(text);
        dest.writeParcelable(user, flags);
        dest.writeParcelable(status, flags);
    }
    private Comment(Parcel in){
        created_at = in.readString();
        id = in.readLong();
        text = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        status = in.readParcelable(Status.class.getClassLoader());
    }
    public static final Parcelable.Creator<Comment> CREATOR
            = new Parcelable.Creator<Comment>(){

        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}

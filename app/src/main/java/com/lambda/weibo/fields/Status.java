package com.lambda.weibo.fields;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class Status implements Parcelable {
//    public static final String CREATED_AT = "created_at";
//    public static final String ID = "id";
//    public static final String MID = "mid";
//    public static final String IDSTR = "idstr";
//    public static final String TEXT = "text";
//    public static final String SOURCE = "source";
//    public static final String FAVORITED = "favorited";
//    public static final String TRUNCATED = "truncated";
//    public static final String IN_REPLY_TO_STATUS_ID = "in_reply_to_status_id";
//    public static final String IN_REPLY_TO_USER_id = "in_reply_to_user_id";
//    public static final String IN_REPLY_TO_SCREEN_ID = "in_reply_to_screen_name";
//    public static final String GEO = "geo";
//    public static final String ANNOTATIONS = "annotations";
//    public static final String REPOSTS_COUNT = "reposts_count";
//    public static final String COMMENTS_COUNT = "comments_count";
//    public static final String USER = "user";

    public static final Parcelable.Creator<Status> CREATOR
            = new Parcelable.Creator<Status>() {

        @Override
        public Status createFromParcel(Parcel source) {
            return new Status(source);
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };
    private String created_at;
    private long id;
    private long mid;
    private String idstr;
    private String text;
    private String source;
    private boolean favorited;
    private boolean truncated;
    private String in_reply_to_status_id;
    private String in_reply_to_user_id;
    private String in_reply_to_screen_id;
    private String thumbnail_pic;
    private String bmiddle_pic;
    private String original_pic;
    private Geo geo;
    private User user;
    private Status retweeted_status;
    //private String annotations;
    private int reposts_count;
    private int comments_count;
    private int attitudes_count;
    private ImageUrl[] pic_urls;
    private Status(Parcel in) {
        id = in.readLong();
        created_at = in.readString();
        user = in.readParcelable(User.class.getClassLoader());
        text = in.readString();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getThumbnail_pic() {
        return thumbnail_pic;
    }

    public ImageUrl[] getPic_urls() {
        return pic_urls;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(created_at);
        dest.writeParcelable(user, flags);
        dest.writeString(text);
    }
}

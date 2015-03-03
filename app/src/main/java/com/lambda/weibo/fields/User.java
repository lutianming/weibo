package com.lambda.weibo.fields;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class User implements Parcelable {
    public static final String ID = "id";
    public static final String SCREEN_NAME = "screen_name";
    public static final String NAME = "name";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String LOCATION = "location";
    public static final String DEST = "description";
    public static final String URL = "url";
    public static final String PROFILE_IMG_URL = "profile_image_url";
    public static final String DOMAIN = "domain";
    public static final String GENDER = "gender";
    public static final String FOLLOWES_COUNT = "followers_count";
    public static final String FRIENDS_COUNT = "friends_count";
    public static final String STATUSES_COUNT = "statuses_count";
    public static final String FAVOURITES_COUNT ="favourites_count";
    public static final String CREATED_AT = "created_at";
    public static final String FOLLOWING = "following";
    public static final String ALLOW_ALL_ACT_MSG = "allow_all_act_msg";
    public static final String GEO_ENABLED = "geo_enabled";
    public static final String VERIFIED = "verified";
    public static final String STATUS = "status";
    public static final String ALLOW_ALL_COMMENT = "allow_all_comment";
    public static final String AVATAR_LARGE = "avatar_large";
    public static final String VERIFIED_REASON = "verified_reason";
    public static final String FOLLOW_ME = "follow_me";
    public static final String ONLINE_STATUES = "online_status";
    public static final String BI_FOLLOERS_COUNT = "bi_followers_count";
    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>(){

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    private long id;
    private String idstr;
    private String screen_name;
    private String name;
    private int province;
    private int city;
    private String location;
    private String description;
    private String url;
    private String profile_image_url;
    private String profile_url;
    private String domain;
    private String weihao;
    private String gender;
    private int followes_count;
    private int friends_count;
    private int statuses_count;
    private int favourites_count;
    private String created_at;
    private boolean following;
    private boolean allow_all_act_msg;
    private boolean geo_enabled;
    private boolean verified;

    private String remark;
    private Status status;
    private boolean allow_all_comment;
    private String avatar_large;
    private String avater_hd;
    private String verified_reason;
    private boolean follow_me;
    private int online_statues;
    private int bi_folloers_count;
    private String lang;

    private User(Parcel in){
        id = in.readLong();
        screen_name = in.readString();
        name = in.readString();
        city = in.readInt();
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", idstr='" + idstr + '\'' +
                ", screen_name='" + screen_name + '\'' +
                ", name='" + name + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", profile_image_url='" + profile_image_url + '\'' +
                ", domain='" + domain + '\'' +
                ", gender='" + gender + '\'' +
                ", weihao='" + weihao + '\'' +
                ", followes_count=" + followes_count +
                ", friends_count=" + friends_count +
                ", statuses_count=" + statuses_count +
                ", favourites_count=" + favourites_count +
                ", created_at='" + created_at + '\'' +
                ", following=" + following +
                ", allow_all_act_msg=" + allow_all_act_msg +
                ", geo_enabled=" + geo_enabled +
                ", verified=" + verified +
                ", remark='" + remark + '\'' +
                ", status=" + status +
                ", allow_all_comment=" + allow_all_comment +
                ", avater_hd='" + avater_hd + '\'' +
                ", avatar_large='" + avatar_large + '\'' +
                ", verified_reason='" + verified_reason + '\'' +
                ", follow_me=" + follow_me +
                ", online_statues=" + online_statues +
                ", bi_folloers_count=" + bi_folloers_count +
                ", lang='" + lang + '\'' +
                '}';
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(screen_name);
        dest.writeString(name);
        dest.writeInt(city);
    }
}

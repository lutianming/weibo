package com.lambda.weibo.fields;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class User implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

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
    private int followers_count;
    private int friends_count;
    private int statuses_count;
    private int favourites_count;
    private String created_at;
    private boolean following;
    private boolean allow_all_act_msg;
    private boolean geo_enabled;
    private boolean verified;
    private String remark;

    public int getFollowers_count() {
        return followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public Status getStatus() {

        return status;
    }

    private Status status;
    private boolean allow_all_comment;
    private String avatar_large;
    private String avater_hd;
    private String verified_reason;
    private boolean follow_me;
    private int online_statues;
    private int bi_folloers_count;
    private String lang;
    private User(Parcel in) {
        id = in.readLong();
        screen_name = in.readString();
        name = in.readString();
        city = in.readInt();
    }

    public String getDescription() {
        return description;
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
                ", followers_count=" + followers_count +
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

package com.lambda.weibo.uris;

import android.net.Uri;

/**
 * Created by LU Tianming on 15-3-1.
 */
public class UsersUri extends BaseUri {
    public static String show(String access_token, String uid) {
        Uri.Builder builder = getBuilder()
                .appendPath("show.json")
                .appendQueryParameter("access_token", access_token)
                .appendQueryParameter("uid", uid);
        return builder.build().toString();
    }

    public static String counts(String access_token, String uids) {
        Uri.Builder builder = getBuilder()
                .appendPath("counts.json")
                .appendQueryParameter("access_token", access_token)
                .appendQueryParameter("uids", uids);
        return builder.build().toString();
    }

    private static Uri.Builder getBuilder(){
        Uri.Builder builder = PREFIX.buildUpon()
                .appendPath("2")
                .appendPath("users");
        return builder;
    }
}

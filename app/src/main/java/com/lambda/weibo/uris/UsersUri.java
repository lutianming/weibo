package com.lambda.weibo.uris;

import android.net.Uri;

/**
 * Created by LU Tianming on 15-3-1.
 */
public class UsersUri extends BaseUri {
    public static String show(String access_token, String uid) {
        Uri.Builder builder = PREFIX.buildUpon()
                .appendPath("users/show.json")
                .appendQueryParameter("access_token", access_token)
                .appendQueryParameter("uid", uid);
        return builder.build().toString();
    }

    public static String counts(String access_token, String uids) {
        Uri.Builder builder = PREFIX.buildUpon()
                .appendPath("users/counts.json")
                .appendQueryParameter("access_token", access_token)
                .appendQueryParameter("uids", uids);
        return builder.build().toString();
    }
}

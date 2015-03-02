package com.lambda.weibo.uris;

import android.net.Uri;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class CommentsUri extends BaseUri{
    public static String show(String access_token, long id){
        Uri.Builder b = getBuilder()
                .appendPath("show.json")
                .appendQueryParameter("access_token", access_token)
                .appendQueryParameter("id", String.valueOf(id));
        return b.build().toString();
    }

    private static Uri.Builder getBuilder(){
        Uri.Builder b = PREFIX.buildUpon()
                .appendPath("2")
                .appendPath("comments");
        return b;
    }
}

package com.lambda.weibo.uris;

import android.net.Uri;

/**
 * Created by LU Tianming on 15-3-1.
 */
public class StatusesUri extends BaseUri{
    public static String publicTimeLine(String access_token, int count) {
        Uri.Builder builder = getBuilder()
                .appendPath("public_timeline.json")
                .appendQueryParameter("access_token", access_token)
                .appendQueryParameter("count", String.valueOf(count));

        return builder.build().toString();
    }

    public static String friendsTimeLine(String access_token, int count){
        Uri.Builder builder = getBuilder()
                .appendPath("friends_timeline.json")
                .appendQueryParameter("access_token", access_token)
                .appendQueryParameter("count", String.valueOf(count))
                .appendQueryParameter("page", String.valueOf(1));

        return builder.build().toString();
    }
    public static String friendsTimeLine(String access_token, long since_id,
                                         long max_id, long count, long page, long baseapp,
                                         long feature, long trim_user){
        Uri.Builder builder = getBuilder()
                .appendPath("friends_timeline.json")
                .appendQueryParameter("access_token", access_token)
                .appendQueryParameter("since_id", String.valueOf(since_id))
                .appendQueryParameter("max_id", String.valueOf(max_id))
                .appendQueryParameter("count", String.valueOf(count))
                .appendQueryParameter("page", String.valueOf(page))
                .appendQueryParameter("baseapp", String.valueOf(baseapp))
                .appendQueryParameter("feature", String.valueOf(feature))
                .appendQueryParameter("trim_user", String.valueOf(trim_user));
        return builder.build().toString();
    }

    public static String friendTimeLineIDs(String access_token){
        Uri.Builder b = getBuilder()
                .appendPath("friends_timeline")
                .appendPath("ids.json")
                .appendQueryParameter("access_token", access_token);
        return b.build().toString();
    }

    private static Uri.Builder getBuilder(){
        Uri.Builder builder = PREFIX.buildUpon()
                .appendPath("2")
                .appendPath("statuses");
        return builder;
    }
}

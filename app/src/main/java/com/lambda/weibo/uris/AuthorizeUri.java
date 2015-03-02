package com.lambda.weibo.uris;

import android.net.Uri;

/**
 * Created by LU Tianming on 15-3-1.
 */
public class AuthorizeUri extends BaseUri {
    public static String authorize(String clientID, String redirectUrl){
        Uri uri = Uri.parse("https://open.weibo.cn/oauth2/authorize");
        Uri.Builder b = uri.buildUpon()
                .appendQueryParameter("client_id", clientID)
                .appendQueryParameter("redirect_uri", redirectUrl)
                .appendQueryParameter("display", "mobile");
        return b.build().toString();
    }
    public static String accessToken(String clientID, String clientSecret, String code, String redirectUri) {
        Uri.Builder builder = getBuilder()
                .appendPath("access_token")
                .appendQueryParameter("client_id", clientID)
                .appendQueryParameter("client_secret", clientSecret)
                .appendQueryParameter("grant_type", "authorization_code")
                .appendQueryParameter("code", code)
                .appendQueryParameter("redirect_uri", redirectUri);
        return builder.build().toString();
    }
    public static String getTokenInfo(String accessToken){
        Uri.Builder b = getBuilder()
                .appendPath("get_token_info")
                .appendQueryParameter("access_token", accessToken);
        return b.build().toString();
    }

    private static Uri.Builder getBuilder(){
        Uri.Builder builder = PREFIX.buildUpon()
                .appendPath("oauth2");
        return builder;
    }
}

package com.lambda.weibo.actions;

import android.app.Activity;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class Action{
    String name;
    int resId;
    Activity activity;
    public String getName() {
        return name;
    }

    public int getResId() {
        return resId;
    }

    public Action(Activity activity){
        this.activity = activity;
    }
}

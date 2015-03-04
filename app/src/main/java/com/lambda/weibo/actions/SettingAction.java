package com.lambda.weibo.actions;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class SettingAction extends Action implements View.OnClickListener{
    public SettingAction(Activity activity) {
        super(activity);
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
    }
}

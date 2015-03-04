package com.lambda.weibo.actions;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fragments.UserFragment;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class UserAction extends Action implements View.OnClickListener {
    public UserAction(Activity activity) {
        super(activity);
        name = "User";
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = activity.getFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction transaction = manager.beginTransaction();
        UserFragment fragment = UserFragment.newInstance(null, null);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
    public void doAction(){

    }
}

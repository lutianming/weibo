package com.lambda.weibo.listeners;

import android.app.Activity;
import android.app.FragmentManager;
import com.lambda.weibo.actions.StatusesAction;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fragments.StatusesFragment;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

/**
 * Created by LU Tianming on 15-3-5.
 */
public class StatusesRefreshListener implements SwipyRefreshLayout.OnRefreshListener {
    private Activity activity;

    public StatusesRefreshListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
        StatusesAction action = new StatusesAction(activity);
        FragmentManager manager = activity.getFragmentManager();
        StatusesFragment fragment = (StatusesFragment)manager.findFragmentById(R.id.fragment_container);
        action.doAction(fragment);
    }
}

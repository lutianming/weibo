package com.lambda.weibo.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lambda.weibo.actions.Action;
import com.lambda.weibo.actions.StatusesAction;
import com.lambda.weibo.actions.UserAction;
import com.lambda.weibo.app.R;
import com.lambda.weibo.viewholders.ItemViewHolder;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class DrawerItemAdapter extends RecyclerView.Adapter{
    private Action[] actions;

    public  DrawerItemAdapter(Activity activity){
        actions = new Action[]{
                new StatusesAction(activity),
                new UserAction(activity),
        };

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_drawer_item, parent, false);
        ItemViewHolder vh = new ItemViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Action action = actions[position];
        ItemViewHolder vh = (ItemViewHolder) holder;
        vh.update(action);
    }

    @Override
    public int getItemCount() {
        return actions.length;
    }

}

package com.lambda.weibo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.viewholders.StatusViewHolder;
import com.lambda.weibo.viewholders.UserInfoViewHolder;

import java.util.ArrayList;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class UserAdapter extends RecyclerView.Adapter {
    private static final int USER = 0;
    private static final int STATUS = 1;

    private User user;
    private ArrayList<Status> statuses;

    public UserAdapter(User user, ArrayList<Status> statuses){
        this.user = user;
        this.statuses = statuses;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == USER){
            View view = inflater.inflate(R.layout.view_user, parent, false);
            RecyclerView.ViewHolder vh = new UserInfoViewHolder(view);
            return vh;
        }else{
            View view = inflater.inflate(R.layout.view_status, parent, false);
            RecyclerView.ViewHolder vh = new StatusViewHolder(view);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            UserInfoViewHolder vh = (UserInfoViewHolder) holder;
            vh.update(user);
        }else{
            Status status = statuses.get(position+1);
            StatusViewHolder vh = (StatusViewHolder) holder;
            vh.update(status);
        }
    }

    @Override
    public int getItemCount() {
        return statuses.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return USER;
        }
        else{
            return STATUS;
        }
    }
}

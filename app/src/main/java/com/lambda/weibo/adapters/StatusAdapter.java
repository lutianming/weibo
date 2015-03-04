package com.lambda.weibo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.viewholders.StatusViewHolder;

import java.util.ArrayList;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class StatusAdapter extends RecyclerView.Adapter<StatusViewHolder> {
    private static final String TAG = "StatusAdapter";
    private int totalNumber;
    private ArrayList<Status> statuses;

    public StatusAdapter(ArrayList<Status> statuses1) {
        this.statuses = statuses1;
    }

    @Override
    public StatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_status, parent, false);
        StatusViewHolder vh = new StatusViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StatusViewHolder holder, int position) {
        Status status = statuses.get(position);
        holder.update(status);
    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

}

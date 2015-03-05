package com.lambda.weibo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Comment;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.viewholders.CommentViewHolder;
import com.lambda.weibo.viewholders.StatusViewHolder;

import java.util.ArrayList;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class StatusWithCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "StatusWithCommentAdapter";
    private static final int STATUS_VIEW = 0;
    private static final int COMMENT_VIEW = 1;
    private int totalNumber;
    private ArrayList<Comment> comments;
    private Status status;
    public StatusWithCommentAdapter(Status status, ArrayList<Comment> comments1)
    {
        this.status = status;
        comments = comments1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if(viewType == STATUS_VIEW){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_status, parent, false);
            holder = new StatusViewHolder(view);

        }
        else{
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_comment, parent, false);
            holder = new CommentViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            StatusViewHolder vh = (StatusViewHolder) holder;
            vh.update(status);
        }else{
            Comment comment = comments.get(position-1);
            CommentViewHolder vh = (CommentViewHolder) holder;
            vh.update(comment);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return STATUS_VIEW;
        }
        else{
            return COMMENT_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return comments.size()+1;
    }

}

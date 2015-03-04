package com.lambda.weibo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Comment;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.requests.RequestHandler;

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

    public static class CommentViewHolder extends RecyclerView.ViewHolder{
        private NetworkImageView profileImageView;
        private TextView nameTextView;
        private TextView commentTextView;
        private ImageLoader imageLoader;

        public CommentViewHolder(View itemView) {
            super(itemView);
            profileImageView = (NetworkImageView) itemView.findViewById(R.id.comment_profile_image_view);
            nameTextView = (TextView)itemView.findViewById(R.id.comment_name_textview);
            commentTextView = (TextView)itemView.findViewById(R.id.comment_text_textview);
            imageLoader = RequestHandler.getInstance(itemView.getContext()).getImageLoader();
        }
        public void update(Comment comment){
            User user = comment.getUser();
            String name = user.getName();
            String imageUrl = user.getProfile_image_url();
            String text = comment.getText();

            nameTextView.setText(name);
            commentTextView.setText(text);
            profileImageView.setImageUrl(imageUrl, imageLoader);
        }
    }

    public static class StatusViewHolder extends RecyclerView.ViewHolder{
        private NetworkImageView profileImageView;
        private TextView nameTextView;
        private TextView timeTextView;
        private TextView statusTextView;
        private ImageLoader imageLoader;

        public StatusViewHolder(View itemView) {
            super(itemView);
            profileImageView = (NetworkImageView) itemView.findViewById(R.id.status_profile_image_view);
            nameTextView = (TextView)itemView.findViewById(R.id.name_textview);
            timeTextView = (TextView) itemView.findViewById(R.id.time_textview);
            statusTextView = (TextView)itemView.findViewById(R.id.status_textview);
            imageLoader = RequestHandler.getInstance(itemView.getContext()).getImageLoader();
        }
        public void update(Status status){
            User user = status.getUser();

            nameTextView.setText(user.getName());
            timeTextView.setText(status.getCreated_at());
            statusTextView.setText(status.getText());
            profileImageView.setImageUrl(user.getProfile_image_url(), imageLoader);
        }
    }
}

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
import com.lambda.weibo.fields.User;
import com.lambda.weibo.requests.RequestHandler;

import java.util.ArrayList;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private static final String TAG = "CommentAdapter";
    private int totalNumber;
    private ArrayList<Comment> comments;
    public CommentAdapter(ArrayList<Comment> comments1){
            comments = comments1;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.update(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private NetworkImageView profileImageView;
        private TextView nameTextView;
        private TextView commentTextView;
        private ImageLoader imageLoader;

        public ViewHolder(View itemView) {
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
}

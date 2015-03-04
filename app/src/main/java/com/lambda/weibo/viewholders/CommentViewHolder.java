package com.lambda.weibo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Comment;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.requests.RequestHandler;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {
    private NetworkImageView profileImageView;
    private TextView nameTextView;
    private TextView commentTextView;
    private ImageLoader imageLoader;

    public CommentViewHolder(View itemView) {
        super(itemView);
        profileImageView = (NetworkImageView) itemView.findViewById(R.id.comment_profile_image_view);
        nameTextView = (TextView) itemView.findViewById(R.id.comment_name_textview);
        commentTextView = (TextView) itemView.findViewById(R.id.comment_text_textview);
        imageLoader = RequestHandler.getInstance(itemView.getContext()).getImageLoader();
    }

    public void update(Comment comment) {
        User user = comment.getUser();
        String name = user.getName();
        String imageUrl = user.getProfile_image_url();
        String text = comment.getText();

        nameTextView.setText(name);
        commentTextView.setText(text);
        profileImageView.setImageUrl(imageUrl, imageLoader);
    }
}

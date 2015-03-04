package com.lambda.weibo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.requests.RequestHandler;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class StatusDetailViewHolder extends RecyclerView.ViewHolder {
    private NetworkImageView profileImageView;
    private TextView nameTextView;
    private TextView timeTextView;
    private TextView statusTextView;
    private ImageLoader imageLoader;

    public StatusDetailViewHolder(View itemView) {
        super(itemView);
        profileImageView = (NetworkImageView) itemView.findViewById(R.id.status_profile_image_view);
        nameTextView = (TextView) itemView.findViewById(R.id.name_textview);
        timeTextView = (TextView) itemView.findViewById(R.id.time_textview);
        statusTextView = (TextView) itemView.findViewById(R.id.status_textview);
        imageLoader = RequestHandler.getInstance(itemView.getContext()).getImageLoader();
    }

    public void update(Status status) {
        User user = status.getUser();

        nameTextView.setText(user.getName());
        timeTextView.setText(status.getCreated_at());
        statusTextView.setText(status.getText());
        profileImageView.setImageUrl(user.getProfile_image_url(), imageLoader);
    }
}

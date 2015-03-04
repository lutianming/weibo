package com.lambda.weibo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lambda.weibo.fields.User;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class UserInfoViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView location;
    private TextView description;
    private NetworkImageView profileImage;
    private ImageLoader imageLoader;

    public UserInfoViewHolder(View itemView) {
        super(itemView);

    }
    public void update(User user){
        name.setText(user.getName());
        location.setText(user.getLocation());
        profileImage.setImageUrl(user.getProfile_image_url(), imageLoader);
    }
}

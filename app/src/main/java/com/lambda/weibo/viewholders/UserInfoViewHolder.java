package com.lambda.weibo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.requests.RequestHandler;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class UserInfoViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView location;
    private TextView description;
    private TextView friends;
    private TextView followers;
    private TextView statues;
    private NetworkImageView profileImage;
    private ImageLoader imageLoader;

    public UserInfoViewHolder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name_text_view);
        location = (TextView) itemView.findViewById(R.id.location_text_view);
        description = (TextView) itemView.findViewById(R.id.description_text_view);
        friends = (TextView) itemView.findViewById(R.id.friends_count_text_view);
        followers = (TextView) itemView.findViewById(R.id.followers_count_text_view);
        statues = (TextView) itemView.findViewById(R.id.statuses_count_text_view);

        profileImage = (NetworkImageView) itemView.findViewById(R.id.profile_image_view);
        imageLoader = RequestHandler.getInstance(itemView.getContext()).getImageLoader();
    }
    public void update(User user){
        if(user == null) return;
        name.setText(user.getName());
        location.setText(user.getLocation());
        description.setText(user.getDescription());
        friends.setText(String.valueOf(user.getFriends_count()));
        followers.setText(String.valueOf(user.getFollowers_count()));
        statues.setText(String.valueOf(user.getStatuses_count()));
        profileImage.setImageUrl(user.getProfile_image_url(), imageLoader);
    }
}

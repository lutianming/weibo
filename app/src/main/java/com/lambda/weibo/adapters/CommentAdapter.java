package com.lambda.weibo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.lambda.weibo.app.R;
import com.lambda.weibo.app.RequestHandler;
import com.lambda.weibo.fields.Comment;
import com.lambda.weibo.fields.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private int totalNumber;
    private JSONArray comments;
    public CommentAdapter(JSONObject data){
        try {
            totalNumber = data.getInt("total_number");
            comments = data.getJSONArray("comments");
        } catch (JSONException e) {
            totalNumber = 0;
            comments = null;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JSONObject comment;
        try {
            comment = comments.getJSONObject(position);
            holder.update(comment);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return totalNumber;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton imageButton;
        private TextView nameTextView;
        private TextView commentTextView;
        private ImageLoader imageLoader;

        public ViewHolder(View itemView) {
            super(itemView);
            imageButton = (ImageButton) itemView.findViewById(R.id.comment_icon_button);
            nameTextView = (TextView)itemView.findViewById(R.id.comment_name_textview);
            commentTextView = (TextView)itemView.findViewById(R.id.comment_text_textview);
            imageLoader = RequestHandler.getInstance(itemView.getContext()).getImageLoader();
        }
        public void update(JSONObject comment) throws JSONException {
            JSONObject user = comment.getJSONObject(Comment.USER);
            String name = user.getString(User.NAME);
            String imageUrl = user.getString(User.PROFILE_IMG_URL);
            String text = comment.getString(Comment.TEXT);

            imageLoader.get(imageUrl, ImageLoader.getImageListener(imageButton,
                    R.drawable.ic_action_error, R.drawable.ic_action_error));
            nameTextView.setText(name);
            commentTextView.setText(text);
        }
    }
}

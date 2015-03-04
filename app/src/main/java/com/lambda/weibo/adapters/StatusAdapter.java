package com.lambda.weibo.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lambda.weibo.app.MainActivity;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Comment;
import com.lambda.weibo.fields.ImageUrl;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.fragments.StatusFragment;
import com.lambda.weibo.listeners.StatusImageClickListener;
import com.lambda.weibo.requests.RequestHandler;
import com.lambda.weibo.uris.CommentsUri;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {
    private static final String TAG = "StatusAdapter";
    private int totalNumber;
    private ArrayList<Status> statuses;

    public StatusAdapter(ArrayList<Status> statuses1) {
        this.statuses = statuses1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_status, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Status status = statuses.get(position);
        holder.update(status);
    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView nameView;
        private TextView timeView;
        private TextView statusView;
        private GridView picsGridView;
        private NetworkImageView profileImageView;
        private Status status;
        private ImageLoader imageLoader;

        public ViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.name_textview);
            timeView = (TextView) view.findViewById(R.id.time_textview);
            statusView = (TextView) view.findViewById(R.id.status_textview);
            profileImageView = (NetworkImageView) view.findViewById(R.id.status_profile_image_view);
            picsGridView = (GridView) view.findViewById(R.id.status_pics_grid_view);
            imageLoader = RequestHandler.getInstance(view.getContext()).getImageLoader();
            view.setOnClickListener(this);
        }

        public void update(Status status) {
            this.status = status;
            User user = status.getUser();
            nameView.setText(user.getName());
            profileImageView.setImageUrl(user.getProfile_image_url(), imageLoader);
            timeView.setText(status.getCreated_at());
            statusView.setText(status.getText());
            ArrayList<ImageUrl> pic_urls = status.getPic_urls();

            if (pic_urls != null && pic_urls.size() > 0) {
                ThumbnailAdapter adapter = new ThumbnailAdapter(this.itemView.getContext(), pic_urls);
                int rows = (pic_urls.size()-1) / 3 + 1;
                ViewGroup.LayoutParams params = picsGridView.getLayoutParams();
                params.height = rows*160;
                picsGridView.setLayoutParams(params);
                picsGridView.setAdapter(adapter);
                picsGridView.setOnItemClickListener(new StatusImageClickListener(status.getPic_urls()));

            }


        }

        @Override
        public void onClick(View v) {
            long id = 0;

            final Activity activity = (Activity) v.getContext();
            id = status.getId();
            SharedPreferences preferences = activity.getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
            String access_token = preferences.getString("access_token", "");
            String uri = CommentsUri.show(access_token, id);
            JsonObjectRequest request = new JsonObjectRequest
                    (Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            Type listtype = new TypeToken<ArrayList<Comment>>() {
                            }.getType();
                            String data;
                            try {
                                data = response.getJSONArray("comments").toString(4);
                            } catch (JSONException e) {
                                data = "";
                                e.printStackTrace();
                            }
                            ArrayList<Comment> comments = gson.fromJson(data, listtype);
                            StatusFragment fragment = StatusFragment.newInstance(status, comments);
                            FragmentManager manager = activity.getFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            transaction.replace(R.id.fragment_container, fragment, StatusFragment.TAG);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            RequestHandler.getInstance(activity).addToRequestQueue(request);
        }
    }
}

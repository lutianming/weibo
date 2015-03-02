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
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.lambda.weibo.app.MainActivity;
import com.lambda.weibo.app.R;
import com.lambda.weibo.app.RequestHandler;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.fragments.StatusFragment;
import com.lambda.weibo.uris.CommentsUri;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LU Tianming on 15-3-2.
 */
public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder>{
    private int totalNumber;
    private JSONArray statuses;
    public StatusAdapter(JSONObject json){
        try {
            totalNumber = json.getInt("total_number");
            statuses = json.getJSONArray("statuses");
        } catch (JSONException e) {
            totalNumber = 0;
            statuses = null;
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.status_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            JSONObject status = statuses.getJSONObject(position);
            holder.update(status);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return totalNumber;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        private TextView nameView;
        private TextView timeView;
        private TextView statusView;
        private JSONObject status;
        public ViewHolder(View view) {
            super(view);
            nameView = (TextView)view.findViewById(R.id.name_textview);
            timeView = (TextView)view.findViewById(R.id.time_textview);
            statusView = (TextView)view.findViewById(R.id.status_textview);
            view.setOnClickListener(this);
        }

        public void update(JSONObject status) throws JSONException {
            this.status = status;
            JSONObject user = status.getJSONObject(Status.USER);
            nameView.setText(user.getString(User.NAME));
            timeView.setText(status.getString(Status.CREATED_AT));
            statusView.setText(status.getString(Status.TEXT));
        }

        @Override
        public void onClick(View v) {
            long id = 0;

            try {
                final Activity activity = (Activity) v.getContext();
                id = status.getLong(Status.ID);
                SharedPreferences preferences = activity.getSharedPreferences(MainActivity.PREF_NAME, Context.MODE_PRIVATE);
                String access_token = preferences.getString("access_token", "");
                String uri = CommentsUri.show(access_token, id);
                JsonObjectRequest request = new JsonObjectRequest
                        (Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                StatusFragment fragment = StatusFragment.newInstance(status, response);
                                FragmentManager manager = activity.getFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.fragment_container, fragment);
                                transaction.commit();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                RequestHandler.getInstance(activity).addToRequestQueue(request);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.lambda.weibo.adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.lambda.weibo.actions.Action;
import com.lambda.weibo.actions.StatusesAction;
import com.lambda.weibo.actions.UserAction;
import com.lambda.weibo.app.MainActivity;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.requests.RequestHandler;
import com.lambda.weibo.uris.UsersUri;
import com.lambda.weibo.viewholders.ItemViewHolder;
import com.lambda.weibo.viewholders.UserInfoViewHolder;
import org.json.JSONObject;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class DrawerItemAdapter extends RecyclerView.Adapter{
    private final static int USER = 0;
    private final static int ITEM = 1;
    private Action[] actions;
    private Activity activity;
    public  DrawerItemAdapter(Activity activity){
        this.activity = activity;
        actions = new Action[]{
                new StatusesAction(activity),
                new UserAction(activity),
        };

    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return USER;
        }else{
            return ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == USER){
            View view = inflater.inflate(R.layout.view_user, parent, false);
            UserInfoViewHolder vh = new UserInfoViewHolder(view);
            return vh;
        }else{
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_drawer_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(view);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position == 0){
            final UserInfoViewHolder vh = (UserInfoViewHolder) holder;
            SharedPreferences preferences = activity.getSharedPreferences(MainActivity.PREF_NAME, Activity.MODE_PRIVATE);
            String access_token = preferences.getString("access_token", "");
            long uid = Long.parseLong(preferences.getString("uid", ""));

            String uri = UsersUri.show(access_token, uid);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Gson gson = new Gson();
                    User user = gson.fromJson(response.toString(), User.class);
                    vh.update(user);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestHandler.getInstance(activity).addToRequestQueue(request);

        }else{
            Action action = actions[position-1];
            ItemViewHolder vh = (ItemViewHolder) holder;
            vh.update(action);
        }
    }

    @Override
    public int getItemCount() {
        return actions.length+1;
    }

}

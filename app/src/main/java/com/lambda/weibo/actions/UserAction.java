package com.lambda.weibo.actions;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lambda.weibo.app.MainActivity;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.fields.User;
import com.lambda.weibo.fragments.UserFragment;
import com.lambda.weibo.requests.RequestHandler;
import com.lambda.weibo.uris.StatusesUri;
import com.lambda.weibo.uris.UsersUri;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class UserAction extends Action implements View.OnClickListener {
    private String TAG = "UserAction";
    public UserAction(Activity activity) {
        super(activity);
        name = "User";
    }

    @Override
    public void onClick(View v) {
        doAction();
        DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();
    }
    public void doAction(){
        FragmentManager manager = activity.getFragmentManager();
        manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction transaction = manager.beginTransaction();

        final UserFragment fragment = UserFragment.newInstance(null, new ArrayList<Status>());
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();

        SharedPreferences preferences = activity.getSharedPreferences(MainActivity.PREF_NAME, Activity.MODE_PRIVATE);
        String access_token = preferences.getString("access_token", "");
        long uid = Long.parseLong(preferences.getString("uid", ""));

        String uri = UsersUri.show(access_token, uid);
        Log.d(TAG, uri);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                User user = gson.fromJson(response.toString(), User.class);
                fragment.setUser(user);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestHandler.getInstance(activity).addToRequestQueue(request);

        uri = StatusesUri.userTimeline(access_token, uid);
        JsonObjectRequest statusesRequest = new JsonObjectRequest(
                Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Status>>() {}.getType();
                String data = "";
                try {
                    data = response.getJSONArray("statuses").toString(4);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ArrayList<Status> statuses = gson.fromJson(data, listType);
                fragment.setStatuses(statuses);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestHandler.getInstance(activity).addToRequestQueue(statusesRequest);
    }
}

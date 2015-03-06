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
import com.lambda.weibo.fragments.StatusesFragment;
import com.lambda.weibo.requests.RequestHandler;
import com.lambda.weibo.uris.StatusesUri;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class StatusesAction extends Action implements View.OnClickListener {
    private String TAG = "StatusesAction";
    private SwipyRefreshLayout refreshLayout;
    private StatusesFragment fragment;
    public StatusesAction(Activity activity) {
        super(activity);
        name = "statuses";
    }

    @Override
    public void onClick(View v) {
        doAction();
        DrawerLayout drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawers();
    }

    public void doAction(StatusesFragment f){
        if(f == null){
            fragment = StatusesFragment.newInstance(new ArrayList<Status>());

            FragmentManager manager = activity.getFragmentManager();
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }else{
            fragment = f;
        }

        refreshLayout = (SwipyRefreshLayout) activity.findViewById(R.id.swipyrefreshlayout);
        if(refreshLayout == null){
            //it shouldn't be null!
        }else{
            if(!refreshLayout.isRefreshing()){
                refreshLayout.setRefreshing(true);
            }
        }

        SharedPreferences preferences = activity.getSharedPreferences(MainActivity.PREF_NAME, Activity.MODE_PRIVATE);
        String access_token = preferences.getString("access_token", "");
        String uid = preferences.getString("uid", "");

        String uri = StatusesUri.friendsTimeLine(access_token, 50);
        Log.d(TAG, uri);
        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<ArrayList<Status>>() {}.getType();
                        String data = response.optJSONArray("statuses").toString();

                        ArrayList<Status> statuses = gson.fromJson(data, listType);
                        fragment.setStatuses(statuses);
                        if(refreshLayout != null){
                            refreshLayout.setRefreshing(false);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        RequestHandler.getInstance(activity).addToRequestQueue(request);
    }

    @Override
    public void doAction(){
        doAction(null);
    }
}

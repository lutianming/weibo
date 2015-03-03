package com.lambda.weibo.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lambda.weibo.fields.AuthInfo;
import com.lambda.weibo.fields.Status;
import com.lambda.weibo.fragments.StatusFragment;
import com.lambda.weibo.fragments.StatusesFragment;
import com.lambda.weibo.requests.RequestHandler;
import com.lambda.weibo.uris.AuthorizeUri;
import com.lambda.weibo.uris.StatusesUri;
import com.lambda.weibo.uris.UsersUri;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements
        StatusesFragment.OnFragmentInteractionListener, StatusFragment.OnFragmentInteractionListener{
    private static final String TAG = "main activity";
    public static final String PREF_NAME = "USER";

    public static final String APP_KEY = "3286800287";
    public static final String APP_SECRET = "d7ed56406692288f0d0be369772d9093";
    public static final String REDIRECT_URL = "https://github.com/lutianming";

    static final int AUTHORIZE_REQUEST = 1;

    private String access_token;
    private String expires_in;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        //main content

        //drawer
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("opened");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("closed");
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        isAuthorized();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.home:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void isAuthorized(){
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        if(preferences.contains("access_token")){
            String access_token = preferences.getString("access_token", "");
            String uri = AuthorizeUri.getTokenInfo(access_token);
            JsonObjectRequest request = new JsonObjectRequest
                    (Request.Method.POST, uri, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            AuthInfo info = gson.fromJson(response.toString(), AuthInfo.class);
                            //2 hours
                            if(info.getExpire_in() > 60*60*2){
                                updateStatuses();
                            }else{
                                authorize();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
            RequestHandler.getInstance(this).addToRequestQueue(request);
        }else{
            authorize();
        }
    }
    private void updateStatuses(){
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        access_token = preferences.getString("access_token", "");
        uid = preferences.getString("uid", "");

        String uri = StatusesUri.friendsTimeLine(access_token, 50);
        Log.d(TAG, uri);
        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
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
                        StatusesFragment fragment = StatusesFragment.newInstance(statuses);
                        FragmentManager manager = getFragmentManager();
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
        RequestHandler.getInstance(this).addToRequestQueue(request);
    }
    private void authorize(){
        Intent intent = new Intent(this, AuthorizeActivity.class);
        startActivityForResult(intent, AUTHORIZE_REQUEST);
    }
    public void userInfo(View view) {
        String uri = UsersUri.show(access_token, uid);

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TextView view = (TextView)findViewById(R.id.textView);
                        //view.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        error.printStackTrace();
                    }
                });
        RequestHandler.getInstance(this).addToRequestQueue(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTHORIZE_REQUEST) {
            if (resultCode == RESULT_OK) {
                String code = data.getStringExtra("code");

                String url = AuthorizeUri.accessToken(APP_KEY, APP_SECRET, code, REDIRECT_URL);

                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Gson gson = new Gson();
                                AuthInfo info = gson.fromJson(response.toString(), AuthInfo.class);

                                SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("access_token", info.getAccess_token());
                                editor.putLong("expires_in", info.getExpire_in());
                                editor.putLong("uid", info.getUid());
                                editor.commit();

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub

                            }
                        });

                RequestHandler.getInstance(this).addToRequestQueue(jsObjRequest);
            }
        }
    }

    @Override
    public void onStatusSelected(JSONObject status) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

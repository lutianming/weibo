package com.lambda.weibo.listeners;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.ImageUrl;
import com.lambda.weibo.fragments.ImagesFragment;

import java.util.ArrayList;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class StatusImageClickListener implements AdapterView.OnItemClickListener {
    private ArrayList<ImageUrl> urls;
    public StatusImageClickListener(ArrayList<ImageUrl> imageUrls){
        urls = imageUrls;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Activity activity = (Activity) view.getContext();
        FragmentManager manager = activity.getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ImagesFragment fragment = ImagesFragment.newInstance(urls, position);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

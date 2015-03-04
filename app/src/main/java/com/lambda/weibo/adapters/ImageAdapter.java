package com.lambda.weibo.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;
import com.lambda.weibo.fields.ImageUrl;
import com.lambda.weibo.fragments.ImageFragment;

import java.util.ArrayList;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class ImageAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ImageUrl> urls;

    public ImageAdapter(FragmentManager fm, ArrayList<ImageUrl> imageUrls) {
        super(fm);
        urls = imageUrls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }


    @Override
    public Fragment getItem(int position) {
        ImageUrl url = urls.get(position);
        return ImageFragment.newInstance(url);
    }
}

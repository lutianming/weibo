package com.lambda.weibo.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lambda.weibo.app.R;
import com.lambda.weibo.fields.ImageUrl;
import com.lambda.weibo.requests.RequestHandler;

/**
 * Created by LU Tianming on 15-3-3.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ImageLoader imageLoader;
    private ImageUrl[] imageUrls;


    public ImageAdapter(Context c, ImageUrl[] pics) {
        mContext = c;
        imageLoader = RequestHandler.getInstance(c).getImageLoader();
        this.imageUrls = pics;
    }

    public int getCount() {
        return imageUrls.length;
    }

    public Object getItem(int position) {
        return imageUrls[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        NetworkImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new NetworkImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (NetworkImageView) convertView;
        }
        imageView.setImageResource(R.drawable.ic_action_error);
        ImageUrl url = imageUrls[position];
        imageView.setImageUrl(url.getThumbnail_pic(), imageLoader);
        return imageView;
    }
}

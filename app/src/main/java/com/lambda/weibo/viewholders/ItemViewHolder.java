package com.lambda.weibo.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.lambda.weibo.actions.Action;
import com.lambda.weibo.app.R;

/**
 * Created by LU Tianming on 15-3-4.
 */
public class ItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textView;
    public ItemViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.item_image_view);
        textView = (TextView) itemView.findViewById(R.id.item_text_view);
    }
    public void update(Action action){
        imageView.setImageResource(action.getResId());
        textView.setText(action.getName());
        this.itemView.setOnClickListener((View.OnClickListener)action);
    }

}

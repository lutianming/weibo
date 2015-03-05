package com.lambda.weibo.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by LU Tianming on 15-3-5.
 */
public class StatusesScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0;
    private int threshold = 200;
    private boolean loading = false;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        Log.d("Scroll", String.valueOf(dy));
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int totalCount = layoutManager.getItemCount();
        int visibleCount = layoutManager.getChildCount();
        int firstVisiblePos = layoutManager.findFirstCompletelyVisibleItemPosition();
        if(loading){
            if(totalCount > previousTotal){
                loading = false;
                previousTotal = totalCount;
            }
        }else if(dy > threshold){
            if(firstVisiblePos == 0){
                //reach start
                loading = true;

            }else if(firstVisiblePos+visibleCount >= totalCount){
                //reach end
                loading = true;

            }
        }



    }
}

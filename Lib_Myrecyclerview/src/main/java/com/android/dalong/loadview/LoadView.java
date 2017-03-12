package com.android.dalong.loadview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.android.dalong.MyRecyclerView;

/**
 * Created by Administrator on 2016/3/23.
 */
public class LoadView {

    private MyRecyclerView recyclerView;
    private Activity context;
    private EndlessRecyclerOnScrollListener mEndlessListener;
    private int footHeigh;

    private int pageSize=10;

    private OnLoadRecyclerListener onLoadRecyclerListener;

    public void setOnLoadRecyclerListener(OnLoadRecyclerListener onLoadRecyclerListener) {
        this.onLoadRecyclerListener = onLoadRecyclerListener;
    }

    /**
     * pageSize只 第一次加载数据的限制（当第一次加载的数目小于pageSize的话不会显示加载更多）
     * */
    public LoadView(final Activity context, final MyRecyclerView recyclerView, final int pageSize) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.pageSize = pageSize;
        if(recyclerView!=null){

            mEndlessListener = new EndlessRecyclerOnScrollListener() {
                @Override
                public void onLoadNextPage(View view) {
                    super.onLoadNextPage(view);

                    LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(recyclerView);
                    if (state == LoadingFooter.State.Loading||state== LoadingFooter.State.TheEnd||state== LoadingFooter.State.NetWorkError) {
                        return;
                    }

                    RecyclerViewStateUtils.setFooterViewState(context, recyclerView, pageSize, LoadingFooter.State.Loading, null);

                    if(onLoadRecyclerListener!=null) {
                        onLoadRecyclerListener.LoadPage(view);
                    }
                }
            };

            recyclerView.setOnScrollListener(mEndlessListener);
        }

        footHeigh = dip2px(context,40);

    }

    /**
     * pageSize只 第一次加载数据的限制（当第一次加载的数目小于pageSize的话不会显示加载更多）
     * */
    public LoadView(final Activity context, final MyRecyclerView recyclerView, final int pageSize, final boolean flag) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.pageSize = pageSize;
        if(recyclerView!=null){

            mEndlessListener = new EndlessRecyclerOnScrollListener() {
                @Override
                public void onLoadNextPage(View view) {
                    super.onLoadNextPage(view);
                    LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(recyclerView);
                    if (state == LoadingFooter.State.Loading||state== LoadingFooter.State.TheEnd||state== LoadingFooter.State.NetWorkError) {
                        return;
                    }

                    RecyclerViewStateUtils.setFooterViewState(context, recyclerView, pageSize, LoadingFooter.State.Loading, null,flag);

                    if(onLoadRecyclerListener!=null) {
                        onLoadRecyclerListener.LoadPage(view);
                    }
                }
            };


            recyclerView.setOnScrollListener(mEndlessListener);
        }

        footHeigh = dip2px(context,40);

    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public void setGlidManager(int spanCount){

        GridLayoutManager manager=new GridLayoutManager(context,spanCount);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter)recyclerView.getAdapter(),manager.getSpanCount()));
        recyclerView.setLayoutManager(manager);
    }
    public void setExstagManager(int spanCount){
        ExStaggeredGridLayoutManager manager=new ExStaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) recyclerView.getAdapter(), manager.getSpanCount()));
        recyclerView.setLayoutManager(manager);
    }
    public void setLinearManager(){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }



    public void LoadState( LoadingFooter.State state){
        RecyclerViewStateUtils.setFooterViewState(recyclerView, state);
    }

    public void LoadStateError(LoadingFooter.State state, View.OnClickListener onClickListener){
        RecyclerViewStateUtils.setFooterViewState(context,recyclerView,pageSize,state,onClickListener);
    }



    public void addHeader(int layoutid){
        View view= LayoutInflater.from(context).inflate(layoutid,null);
        RecyclerViewUtils.setHeaderView(recyclerView, view);
    }


    public void addHeader(View view ){
        RecyclerViewUtils.setHeaderView(recyclerView,view);
    }

    public void setShowTitleListener(EndlessRecyclerOnScrollListener.ShowTitleListener showTitleListener, int height){
        if(mEndlessListener!=null){
            mEndlessListener.setScrollThreshold(height,showTitleListener,footHeigh);
        }
    }

    public interface  OnLoadRecyclerListener{
        public void LoadPage(View view);
    }

}

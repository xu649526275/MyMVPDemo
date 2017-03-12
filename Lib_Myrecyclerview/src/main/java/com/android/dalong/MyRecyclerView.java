package com.android.dalong;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.android.dalong.loadview.HeaderAndFooterRecyclerViewAdapter;
import com.android.dalong.loadview.LoadView;
import com.android.dalong.loadview.LoadingFooter;
import com.android.dalong.loadview.RecyclerViewUtils;


/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class MyRecyclerView extends RecyclerView {

    private LoadView loadView;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

//
//    public void setAdapters(Adapter adapter){
//
//        this.setAdapter(myadapter);
//    }

    @Override
    public void setAdapter(Adapter adapter) {
        HeaderAndFooterRecyclerViewAdapter myadapter = new HeaderAndFooterRecyclerViewAdapter(adapter);
        super.setAdapter(myadapter);
    }

    public void setLoadView(Activity activity, int pagesize, LoadView.OnLoadRecyclerListener loadRecyclerListener){
        loadView = new LoadView(activity, this, pagesize);
        loadView.setOnLoadRecyclerListener(loadRecyclerListener);
    }


    public void loadTheEnd(){
        loadView.LoadState(LoadingFooter.State.TheEnd);
    }


    public void loadNetWorkError(OnClickListener clickListener){
        loadView.LoadStateError(LoadingFooter.State.NetWorkError,clickListener);
    }

    public void loadSuccess(){
        loadView.LoadState(LoadingFooter.State.Normal);
    }

    public void loadLoading(){
        loadView.LoadState(LoadingFooter.State.Loading);
    }


    public void addHeaderView(View view){
        loadView.addHeader(view);
    }













}

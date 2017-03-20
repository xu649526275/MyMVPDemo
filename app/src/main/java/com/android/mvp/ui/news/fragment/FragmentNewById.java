package com.android.mvp.ui.news.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.mvp.R;
import com.android.mvp.base.BaseFragmentLazy;
import com.android.mvp.bean.WYNewsBean;
import com.android.mvp.presenter.NewByIdPresenter;
import com.android.mvp.presenter.contract.NewsContract;
import com.android.mvp.ui.news.adapter.NewsAdapter;
import com.android.mvp.util.MLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 新闻列表， 没有加加载更多，和下拉刷新，下次更新的时候添加
 * Created by Administrator on 2016/9/29 0029.
 */

public class FragmentNewById extends BaseFragmentLazy<NewByIdPresenter, List<WYNewsBean>> implements NewsContract.NewsView {


    private static final String TAG = "FragmentNewById";

    @Bind(R.id.my_recycler)
    RecyclerView mMyRecycler;

    private NewsAdapter adapter;
    private List<WYNewsBean> newLists = new ArrayList<WYNewsBean>();
    private String type;
    private String id;
    private int index = 0;





    /**
     * 初始化view
     * */
    @Override
    protected void initView(View RootView) {
        mPresenter.attachView(this, getActivity());
        Bundle bundle = getArguments();
        type = bundle.getString("type");
        id = bundle.getString("id");
        initView();
    }

    @Override
    public void initView() {
        adapter = new NewsAdapter(getActivity(), newLists);
        LinearLayoutManager lm=new LinearLayoutManager(getActivity());
        mMyRecycler.setAdapter(adapter);
        mMyRecycler.setLayoutManager(lm);
        isPrepared=true;
        lazyLoad();
    }


    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_new_id;
    }

    @Override
    protected void lazyLoad() {
        if(!isPrepared||!isVisible||mHasLoadedOnce){
            return;
        }
        initData();
    }


    /**
     * 初始化数据
     * */
    @Override
    protected void initData() {
        showProgress();
        index = 0;
        newLists.clear();
        mPresenter.getNewById(type, id, index);
    }



    @Override
    public NewByIdPresenter getPresenter() {
        return new NewByIdPresenter();
    }


    @Override
    public void showDataSuccess(List<WYNewsBean> datas) {
        super.showDataSuccess(datas);
        if(datas!=null){
            MLog.v(TAG,"数据加载成功"+datas.size());
            newLists.addAll(datas);
            adapter.setData(newLists);
            mHasLoadedOnce=true;
        }

    }

    @Override
    public void showDataError(String errorMessage, int tag) {
        super.showDataError(errorMessage, tag);
    }
}

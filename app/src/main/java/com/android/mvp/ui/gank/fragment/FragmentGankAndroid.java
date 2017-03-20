package com.android.mvp.ui.gank.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.dalong.MyRecyclerView;
import com.android.dalong.MyRefLayout;
import com.android.dalong.loadview.LoadView;
import com.android.dalong.ptr.PtrFrameLayout;
import com.android.mvp.R;
import com.android.mvp.base.BaseFragmentLazy;
import com.android.mvp.bean.GankItemBean;
import com.android.mvp.presenter.GankPresenter;
import com.android.mvp.presenter.contract.GankContract;
import com.android.mvp.ui.gank.adapter.GankAndroidAdapter;
import com.android.mvp.util.MLog;
import com.android.mvp.util.ToastShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * 干货-包含Android,ios，前端
 *
 * @autor 徐文龙
 * @time 2016/9/21  14:43
 */
public class FragmentGankAndroid extends BaseFragmentLazy<GankPresenter, List<GankItemBean>> implements GankContract.View {


    private static final String TAG = "FragmentGankAndroid";
    @Bind(R.id.android_recycler)
    MyRecyclerView mAndroidRecycler;
    @Bind(R.id.load_more_list_view_ptr_frame)
    MyRefLayout loadMoreListViewPtrFrame;


    private int pageSize=10;
    private int pageIndex=1;

    private String tech;
    private GankAndroidAdapter mAdapter;
    private List<GankItemBean> mGankItemBens=new ArrayList<GankItemBean>();





    @Override
    protected void initView(View RootView) {
        mPresenter.attachView(this, getActivity());
        Bundle bundle=this.getArguments();
        tech=bundle.getString("tech");
        initView();

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_gank_android;
    }






    /**
     * 初始化view
     * */
    public void initView() {
        super.initView();
        loadMoreListViewPtrFrame.initDefRefresh();
        loadMoreListViewPtrFrame.setRefresh(new MyRefLayout.PtrRefresh() {
            @Override
            public void ptrrefresh(PtrFrameLayout frame) {
                pageIndex = 1;
                loadData();
            }
        });

        mAdapter=new GankAndroidAdapter(getActivity(),mGankItemBens);
        mAndroidRecycler.setAdapter(mAdapter);
        mAndroidRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAndroidRecycler.setLoadView(getActivity(), pageSize, new LoadView.OnLoadRecyclerListener() {
            @Override
            public void LoadPage(View view) {
                loadData();
            }
        });




    }

    private void loadData() {
        mPresenter.getGankTech(tech,pageSize,pageIndex);
    }


    public void initData(){
        showLoadingView();
        pageIndex=1;
        loadData();
    }



    @Override
    public GankPresenter getPresenter() {
        return new GankPresenter();
    }




    @Override
    protected View getLoadingTargetView() {
        return loadMoreListViewPtrFrame;
    }

    @Override
    public void onReload() {
        super.onReload();
        initData();
    }



    @Override
    public void showDataSuccess(List<GankItemBean> datas) {
        super.showDataSuccess(datas);
        if(datas!=null){
            showContent();
            if (pageIndex == 1 && loadMoreListViewPtrFrame != null) {
                mGankItemBens.clear();
                loadMoreListViewPtrFrame.refreshComplete();
            }
            mGankItemBens.addAll(datas);
            if (datas.size() < pageSize) {
                mAndroidRecycler.loadTheEnd();
            } else {
                mAndroidRecycler.loadSuccess();
            }
            mAdapter.setData(mGankItemBens);
            pageIndex++;
            mHasLoadedOnce=true;
        }
    }

    @Override
    public void showDataError(String errorMessage, int tag) {
        super.showDataError(errorMessage, tag);
        if (pageIndex == 1 && loadMoreListViewPtrFrame != null) {
            loadMoreListViewPtrFrame.refreshComplete();
        }
        if (mGankItemBens.size() > 0&&pageIndex>1) {
            mAndroidRecycler.loadNetWorkError(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mAndroidRecycler.loadLoading();
                    loadData();
                }
            });
        }else{
            showNetErrorView();
            ToastShow.getInstance(getActivity().getApplicationContext()).toastShow(errorMessage);
        }
    }
}

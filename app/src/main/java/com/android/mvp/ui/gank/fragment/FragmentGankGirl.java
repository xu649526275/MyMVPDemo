package com.android.mvp.ui.gank.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.android.dalong.MyRecyclerView;
import com.android.dalong.MyRefLayout;
import com.android.dalong.loadview.LoadView;
import com.android.mvp.R;
import com.android.mvp.base.BaseFragmentLazy;
import com.android.mvp.bean.GankItemBean;
import com.android.mvp.presenter.GankPresenter;
import com.android.mvp.presenter.contract.GankContract;
import com.android.mvp.ui.gank.adapter.GankGirlAdapter;
import com.android.mvp.util.ToastShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 美女图片
 *
 * @autor 徐文龙
 * @time 2016/9/21  14:43
 */
public class FragmentGankGirl extends BaseFragmentLazy<GankPresenter,List<GankItemBean>> implements GankContract.View {



    @Bind(R.id.gial_recycler)
    MyRecyclerView mGialRecycler;
    @Bind(R.id.load_more_list_view_ptr_frame)
    MyRefLayout loadMoreListViewPtrFrame;

    private int pageSize=10;
    private int pageIndex=1;

    private GankGirlAdapter mAdapter;

    private List<GankItemBean> mGankItemBens=new ArrayList<GankItemBean>();


    @Override
    protected void initView(View RootView) {
        initView();
        mPresenter.attachView(this, getActivity());
        isPrepared=true;
        lazyLoad();

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_gank_girl;
    }


    public void initView() {
        super.initView();
        loadMoreListViewPtrFrame.initDefRefresh();
        loadMoreListViewPtrFrame.setRefresh(new MyRefLayout.PtrRefresh() {
            @Override
            public void ptrrefresh(com.android.dalong.ptr.PtrFrameLayout frame) {
                pageIndex = 1;
                loadData();
            }
        });
        mAdapter=new GankGirlAdapter(getActivity(),mGankItemBens);
        mGialRecycler.setAdapter(mAdapter);
//        mGialRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mGialRecycler.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        mGialRecycler.setLoadView(getActivity(), pageSize, new LoadView.OnLoadRecyclerListener() {
            @Override
            public void LoadPage(View view) {
                loadData();
            }
        });



    }

    private void loadData() {
        mPresenter.getGankGirl(pageSize,pageIndex);
    }

    public void initData(){
        pageIndex=1;
        loadData();
    }





    @Override
    public GankPresenter getPresenter() {
        return new GankPresenter();
    }


    @Override
    public void showDataError(String errorMessage, int tag) {
        super.showDataError(errorMessage, tag);
        if (pageIndex == 1 && loadMoreListViewPtrFrame != null) {
            loadMoreListViewPtrFrame.refreshComplete();
        }
        if (mGankItemBens.size() > 0&&pageIndex>1) {
            mGialRecycler.loadNetWorkError(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mGialRecycler.loadLoading();
                    loadData();
                }
            });
        }else{
            ToastShow.getInstance(getActivity().getApplicationContext()).toastShow(errorMessage);
        }
    }

    @Override
    public void showDataSuccess(List<GankItemBean> datas) {
        super.showDataSuccess(datas);
        if(datas!=null){
            if (pageIndex == 1 && loadMoreListViewPtrFrame != null) {
                mGankItemBens.clear();
                loadMoreListViewPtrFrame.refreshComplete();
            }
            mGankItemBens.addAll(datas);
            if (datas.size() < pageSize) {
                mGialRecycler.loadTheEnd();
            } else {
                mGialRecycler.loadSuccess();
            }
            mAdapter.setData(mGankItemBens);
            pageIndex++;
            mHasLoadedOnce=true;
        }
    }


}

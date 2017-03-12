package com.android.mvp.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import com.android.mvp.R;
import com.android.mvp.base.BaseFragment;
import com.android.mvp.base.BaseFragmentLazy;
import com.android.mvp.bean.BrandBean;
import com.android.mvp.bean.HomeBanner;
import com.android.mvp.bean.HomePost;
import com.android.mvp.bean.HomeSpecial;
import com.android.mvp.presenter.HomePresenter;
import com.android.mvp.presenter.contract.HomeContract;
import com.android.mvp.util.ToastShow;

import java.util.List;

import butterknife.ButterKnife;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/9  11:18
 */
public class FragmentMain extends BaseFragment<HomePresenter, List<BrandBean>> implements HomeContract.View {







    @Override
    protected int getContentLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View RootView) {
        getPresenter().attachView(this,getActivity());
    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected void initData() {
        getPresenter().getHomeBanner();
    }



    @Override
    public void onReload() {

        ToastShow.getInstance(getActivity()).toastShow("点击了");

        showContent();
    }

    @Override
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }


    @Override
    public void onPostSuccess(List<HomePost> homePosts) {

    }

    @Override
    public void onBannerSuccess(List<HomeBanner> homeBanner) {

    }

    @Override
    public void onSpecialSuccess(List<HomeSpecial> homeSpecial) {

    }

    @Override
    public void onSpecialError(String Message, int errorTag) {

    }


    @Override
    public void showDataSuccess(List<BrandBean> datas) {
        super.showDataSuccess(datas);
    }
}

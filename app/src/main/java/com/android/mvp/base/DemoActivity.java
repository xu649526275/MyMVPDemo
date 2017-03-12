package com.android.mvp.base;

import android.view.View;
import android.widget.FrameLayout;

import com.android.mvp.R;
import com.android.mvp.bean.BrandBean;
import com.android.mvp.bean.HomeBanner;
import com.android.mvp.bean.HomePost;
import com.android.mvp.bean.HomeSpecial;
import com.android.mvp.presenter.HomePresenter;
import com.android.mvp.presenter.contract.HomeContract;
import com.android.mvp.util.ToastShow;

import java.util.List;

import butterknife.Bind;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/12  13:35
 */
public class DemoActivity extends BaseActivity<HomePresenter, List<BrandBean>> implements HomeContract.View {


    @Bind(R.id.my_fragment)
    FrameLayout mMyFragment;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {


    }

    @Override
    protected void initView() {
        showNetErrorView();
//        showEmptyView("没有更多东西了");
        mPresenter.attachView(this, context);
    }


    @Override
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Override
    public View getLoadingTargetView() {
        return mMyFragment;
    }




    @Override
    protected void initData() {

    }

    @Override
    public void onReload() {

        ToastShow.getInstance(context).toastShow("你说话");
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


}

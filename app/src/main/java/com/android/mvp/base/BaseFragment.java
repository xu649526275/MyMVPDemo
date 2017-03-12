package com.android.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mvp.ui.MyApplication;
import com.android.mvp.widget.dialog.LoadingFragmentDialog;
import com.android.mvp.widget.loading.VaryViewHelperController;
import com.squareup.leakcanary.RefWatcher;

import butterknife.ButterKnife;

/**
 * Fragment父类
 *
 * @autor 徐文龙
 * @time 2016/9/8  9:50
 */
public abstract class BaseFragment<T extends BasePresenterI,V> extends Fragment implements BaseViewI<V> {

    private String TAG ;
    protected View mRootView;
    protected T mPresenter;
    private LoadingFragmentDialog loadDialogView;
    protected VaryViewHelperController mVaryViewHelperController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(getContentLayout(), container, false);
            TAG=this.getClass().getSimpleName();
            ButterKnife.bind(this, mRootView);
//            mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
            if (null != getLoadingTargetView()) {
                mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
            }

            if (null != getPresenter()) {
                mPresenter = getPresenter();
            }

            initView(mRootView);
            initToolbar();
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    protected abstract void initData();

    protected abstract void initView(View RootView);

    protected abstract void initToolbar();

    protected abstract int getContentLayout();

    public T getPresenter() {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if(mPresenter!=null){
            mPresenter.unsubcrible();
        }
        if(loadDialogView!=null){
            loadDialogView.dismiss();
        }
        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    /**
     * 具体在哪个部位显示加载中，加载失败，网络异常等
     * */
    protected View getLoadingTargetView() {
        return null;
    }



    /**
     * 显示加载弹框
     */
    @Override
    public void showProgress() {
        if (loadDialogView == null) {
            loadDialogView = LoadingFragmentDialog.createDialog(getActivity());
        }

        loadDialogView.show();
    }

    /**
     * 隐藏加载弹框
     */
    @Override
    public void hideProgress() {
        if (loadDialogView != null) {

            loadDialogView.dismiss();

        }
    }

    @Override
    public void onReload() {

    }

    @Override
    public void showLoadingView() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showLoading();
    }

    @Override
    public void showNetErrorView() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showNetworkError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReload();
            }
        });
    }

    @Override
    public void showEmptyView(String msg) {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showEmpty(msg);
    }

    @Override
    public void showContent() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.restore();
    }


    @Override
    public void showDataError(String errorMessage, int tag) {
        hideProgress();
    }

    @Override
    public void showDataSuccess(V datas) {
        hideProgress();
    }


}

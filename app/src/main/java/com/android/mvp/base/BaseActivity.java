package com.android.mvp.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.android.mvp.R;
import com.android.mvp.ui.MyApplication;
import com.android.mvp.util.GeneralUtil;
import com.android.mvp.util.KeyBoardUtils;
import com.android.mvp.util.MLog;
import com.android.mvp.widget.dialog.LoadingActivityDialog;
import com.android.mvp.widget.loading.VaryViewHelperController;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * BaseActivity父类
 * 徐文龙
 */
public abstract class BaseActivity<T extends BasePresenterI, V> extends AutoLayoutActivity implements BaseViewI<V> {

    private String TAG ;
    public T mPresenter;

    private LoadingActivityDialog loadDialogView;
    protected VaryViewHelperController mVaryViewHelperController;
    public Context context;

    private SystemBarTintManager tintManager;


    private MyApplication application;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        context=this;


        ButterKnife.bind(this);
        TAG=this.getClass().getSimpleName();

        if (null != getPresenter()) {
            mPresenter = getPresenter();
        }
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            setTranslucentStatus(getResources().getColor(R.color.colorPrimaryDark));


        initView();
        initToolbar();
        initData();
        application= (MyApplication) context.getApplicationContext();
        application.addActivity(this);


    }


    /**
     * 设置状态栏背景状态
     * <p/>
     * 4.4以上，5.0以下
     */
    public void setTranslucentStatus(int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setTintColor(color);
    }


    /**
     * Base基本类
     * */
    public abstract int getLayoutId();
    /**
     * 设置toolbar
     * */
    protected abstract void initToolbar();
    /**
     * 设置initView
     * */
    protected abstract void initView();

    protected abstract void initData();

    /**
     * 获取Presenter
     * */
    public T getPresenter() {
        return null;
    }






    /**
     * 显示加载弹框
     */
    @Override
    public void showProgress() {
        if (loadDialogView == null) {
            loadDialogView = LoadingActivityDialog.createDialog(BaseActivity.this);
        }

        loadDialogView.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        application=null;
        if(mPresenter!=null){
            mPresenter.unsubcrible();
        }
        if(loadDialogView!=null){
            loadDialogView.dismiss();
        }


        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(this);
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
    public void showDataError(String errorMessage, int tag) {
        hideProgress();
    }

    @Override
    public void showDataSuccess(V datas) {
        hideProgress();
    }

    /**
     * 加载中的的View
     * */
    @Override
    public void showLoadingView() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.showLoading();
    }

    /**
     * 加载失败的View
     * */
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
    /**
     * 加载不到数据的View
     * */
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
        MLog.v(TAG,"调用");
        mVaryViewHelperController.restore();
    }


    public View getLoadingTargetView() {
        return null;
    }










    /**
     * 沉浸式状态栏：着色
     */
    public void titlestatusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            Window window = getWindow();
            ViewGroup mContentView = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);

            //First translucent status bar.
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            int statusBarHeight = getStatusBarHeight(getApplicationContext());

            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                //如果已经为 ChildView 设置过了 marginTop, 再次调用时直接跳过
                if (lp != null && lp.topMargin < statusBarHeight && lp.height != statusBarHeight) {
                    //不预留系统空间
                    ViewCompat.setFitsSystemWindows(mChildView, false);
                    lp.topMargin += statusBarHeight;
                    mChildView.setLayoutParams(lp);
                }
            }

            View statusBarView = mContentView.getChildAt(0);
            if (statusBarView != null && statusBarView.getLayoutParams() != null && statusBarView.getLayoutParams().height == statusBarHeight) {
                //避免重复调用时多次添加 View
                statusBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                return;
            }
            statusBarView = new View(getApplicationContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
            statusBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            //向 ContentView 中添加假 View
            mContentView.addView(statusBarView, 0, lp);
        }
    }


    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }





    /**
     * 点击空白处影藏输入法
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (GeneralUtil.isHideInput(view, ev)) {
                KeyBoardUtils.HideSoftInput(view.getWindowToken(),
                        getApplicationContext());
            }

        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getLocalClassName()); //统计页面(仅有Activity的应用中SDK自动调用，不需要单独写)
        MobclickAgent.onResume(this);          //统计时长
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getLocalClassName()); // （仅有Activity的应用中SDK自动调用，不需要单独写）保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息
        MobclickAgent.onPause(this);
    }



    /**
     * 添加自定义颜色
     * */
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }


}

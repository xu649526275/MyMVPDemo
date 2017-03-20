package com.android.mvp.base;

import android.content.Context;

import com.android.mvp.util.NetUtil;
import com.android.mvp.util.ToastShow;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/8  10:20
 */
public class BasePresenter<V extends BaseViewI> implements BasePresenterI {



    public V mView;
    public Context context;
    private CompositeSubscription mCompositeSubscription;

    @Override
    public boolean checkNetWork(Context context) {
        if(!NetUtil.isNetworkAvailable()) {
            ToastShow.getInstance(context).toastShow("网络不给力");
        }
        return NetUtil.isNetworkAvailable();
    }


    public void attachView(V view,Context context) {
        this.mView = view;
        this.context=context;
    }


    /**
     * 事件订阅
     * */
    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }


    @Override
    public void unsubcrible() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
        mView=null;
        context=null;
        this.mCompositeSubscription=null;
    }



}

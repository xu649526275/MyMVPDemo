package com.android.mvp.presenter;

import com.android.mvp.base.BasePresenter;
import com.android.mvp.bean.NewTabs;
import com.android.mvp.db.DbUtils;
import com.android.mvp.presenter.contract.NewsContract;
import com.android.mvp.util.MLog;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/29 0029.
 */

public class NewPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {


    private DbUtils mDbUtils;



    @Override
    public void initTabs() {
        mView.showProgress();
        mDbUtils=new DbUtils(context);
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean flag=mDbUtils.initTab();
                MLog.v("DaLong","初始化新闻Tab"+flag);
                subscriber.onNext(flag);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                    queryTabs();
            }
        });

    }


    @Override
    public void queryTabs() {
        mDbUtils=new DbUtils(context);
        Observable.create(new Observable.OnSubscribe<List<NewTabs>>() {
            @Override
            public void call(Subscriber<? super List<NewTabs>> subscriber) {

                subscriber.onNext(mDbUtils.queryNewTabs(true));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<NewTabs>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<NewTabs> newTabses) {
                mView.showDataSuccess(newTabses);
            }
        });
    }



}

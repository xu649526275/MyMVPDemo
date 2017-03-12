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

public class NewTabPresenter extends BasePresenter<NewsContract.TAbView> implements NewsContract.TabPresenter {


    private DbUtils mDbUtils;




    /**
     * 查询数据库，得到我的tab集合
     * */
    @Override
    public void getMyTabs() {
        this.mDbUtils=new DbUtils(context);
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

    /**
     * 查询数据库，得到更多tab集合
     * */
    @Override
    public void getMoreTabs() {
        this.mDbUtils=new DbUtils(context);
        Observable.create(new Observable.OnSubscribe<List<NewTabs>>() {
            @Override
            public void call(Subscriber<? super List<NewTabs>> subscriber) {

                subscriber.onNext(mDbUtils.queryNewTabs(false));
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
                mView.showMoreTabSuccess(newTabses);
            }
        });
    }



    /**
     * 修改我的tab的内容
     * */
    @Override
    public void updateMyItemClick(NewTabs tabs,int position) {
        this.mDbUtils=new DbUtils(context);
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(mDbUtils.updateNewTab(tabs,false));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                MLog.v("DaLongs","修改成功"+integer);
                mView.onMyItemClick(tabs,position);
            }
        });

    }


    /**
     * 修改更多tab的内容
     * */
    @Override
    public void updateMoreItemClick(NewTabs tabs,int position) {
        this.mDbUtils=new DbUtils(context);
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(mDbUtils.updateNewTab(tabs,true));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {

                MLog.v("DaLongs","修改成功"+integer);
                mView.onMoreItemClick(tabs,position);
            }
        });

    }







}

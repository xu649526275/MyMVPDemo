package com.android.mvp.presenter;

import com.android.mvp.base.BasePresenter;
import com.android.mvp.bean.BrandBean;
import com.android.mvp.bean.HomeBanner;
import com.android.mvp.bean.HomePost;
import com.android.mvp.bean.HomeSpecial;
import com.android.mvp.bean.M_Base;
import com.android.mvp.http.ResultTag;
import com.android.mvp.model.HomeModel;
import com.android.mvp.presenter.contract.HomeContract;
import com.android.mvp.util.MLog;

import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/8  10:35
 */
public class HomePresenter extends BasePresenter<HomeContract.View>{

    private static final String TAG = "HomePresenter";

    private HomeModel mModelI;


    public HomePresenter() {
        mModelI=new HomeModel();
    }


    /**
     * 广告
     * */
    public void getHomeBanner(){
        addSubscription(mModelI.getHomeBanner().subscribe(new Subscriber<M_Base<List<HomeBanner>>>() {
            @Override
            public void onCompleted() {
                //   hideProgress();
                MLog.v("DaLong","completed");
            }

            @Override
            public void onError(Throwable e) {
                mView.showDataError("网络异常",ResultTag.ERROR);
                MLog.v("DaLong","error" +e.getMessage());
            }

            @Override
            public void onNext(M_Base<List<HomeBanner>> listM_base) {
                MLog.v(TAG,"banner"+listM_base.getStatus()+"   "+listM_base.getMssage());
                if(listM_base.getStatus()==1){
                    mView.onBannerSuccess(listM_base.getData());
                }else{
                    mView.showDataError(listM_base.getMssage(),listM_base.getStatus());
                }

            }
        }));


    }









    /**
     * 分类
     * */
    public void getBrand() {
        checkNetWork(context);


        Subscription subscription2 = mModelI.homeBrand().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<M_Base<List<BrandBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showDataError("网络异常", ResultTag.ERROR);
                    }

                    @Override
                    public void onNext(M_Base<List<BrandBean>> listM_base) {
                        MLog.v(TAG,"分类"+listM_base.getStatus()+"   "+listM_base.getMssage());
                        if (listM_base.getStatus() == 1) {

                            mView.showDataSuccess(listM_base.getData());
                        } else {
                            mView.showDataError(listM_base.getMssage(), listM_base.getStatus());
                        }
                    }
                });
        addSubscription(subscription2);
    }

    /**
     * 加载社区热帖
     * */
    public void getPost() {

        checkNetWork(context);


        Subscription subscription2 = mModelI.homePost().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<M_Base<List<HomePost>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showDataError("网络异常", ResultTag.ERROR);
                    }

                    @Override
                    public void onNext(M_Base<List<HomePost>> listM_base) {

                        MLog.v(TAG,"社区"+listM_base.getStatus()+"   "+listM_base.getMssage());
                        if (listM_base.getStatus() == 1) {
                            mView.onPostSuccess(listM_base.getData());

                        } else {
                            mView.showDataError(listM_base.getMssage(), listM_base.getStatus());
                        }
                    }
                });
        addSubscription(subscription2);
    }




    /**
     * 加载精选专题
     * */
    public void getSpecial(Map<String, String> map) {


        Subscription subscription1 = mModelI.homeSpecial(map).subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Subscriber<M_Base<List<HomeSpecial>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.hideProgress();
                        mView.showDataError("网络异常", ResultTag.ERROR);
                    }

                    @Override
                    public void onNext(M_Base<List<HomeSpecial>> listM_base) {
                        MLog.v(TAG,"专题"+listM_base.getStatus()+"   "+listM_base.getMssage());
                        mView.hideProgress();
                        if (listM_base.getStatus() == 1) {
                            mView.onSpecialSuccess(listM_base.getData());

                        } else {
                            mView.onSpecialError(listM_base.getMssage(), listM_base.getStatus());
                        }
                    }
                });
        addSubscription(subscription1);
    }


}

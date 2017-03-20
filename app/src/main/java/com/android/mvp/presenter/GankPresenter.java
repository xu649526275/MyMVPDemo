package com.android.mvp.presenter;

import com.android.mvp.base.BasePresenter;
import com.android.mvp.bean.GankHttpResponse;
import com.android.mvp.bean.GankItemBean;
import com.android.mvp.http.ResultTag;
import com.android.mvp.model.GankModel;
import com.android.mvp.presenter.contract.GankContract;
import com.android.mvp.util.ImageLoader;
import com.android.mvp.util.MLog;

import java.util.List;
import java.util.concurrent.ExecutionException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/21  14:36
 */
public class GankPresenter extends BasePresenter<GankContract.View> implements GankContract.Presenter {

    private GankModel mModel;

    public GankPresenter() {
        mModel=new GankModel();
    }

    @Override
    public void getGankGirl(int pageSize,int pageIndex) {


        addSubscription(mModel.getGirlList(pageSize,pageIndex)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Func1<GankHttpResponse<List<GankItemBean>>, Observable<GankItemBean>>() {
                    @Override
                    public Observable<GankItemBean> call(GankHttpResponse<List<GankItemBean>> listGankHttpResponse) {
                        if(listGankHttpResponse.getError()){
                            return Observable.empty();
                        }
                        if(listGankHttpResponse.getResults().size()==0){
                            return Observable.empty();
                        }
                        return Observable.from(listGankHttpResponse.getResults());
                    }
                })//遍历结果给里面添加图片宽和高 ，然后遍历
                .filter(new Func1<GankItemBean, Boolean>() {
                    @Override
                    public Boolean call(GankItemBean gankItemBean) {
                        try {
                            gankItemBean.setPixel(ImageLoader.calePhotoSize(context, gankItemBean.getUrl()));
                            return true;
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                            return false;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }
                }).toList()
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<GankItemBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showDataError("网络异常", ResultTag.ERROR);
                    }

                    @Override
                    public void onNext(List<GankItemBean> gankItemBeen) {
                        mView.showDataSuccess(gankItemBeen);
                    }
                }));

    }



    @Override
    public void getGankTech(String tech, int pageSize, int pageIndex) {
        MLog.v("DaLong",tech+"   "+pageSize+"    "+pageIndex);
        addSubscription(mModel.getTechList(tech,pageSize,pageIndex).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GankHttpResponse<List<GankItemBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                MLog.v("DaLong",""+e.getLocalizedMessage());
                mView.showDataError("网络异常", ResultTag.ERROR);
            }

            @Override
            public void onNext(GankHttpResponse<List<GankItemBean>> listGankHttpResponse) {
                if(!listGankHttpResponse.getError()){
                    if(listGankHttpResponse.getResults()!=null&&listGankHttpResponse.getResults().size()>0){
                        MLog.v("DaLongsss","长度"+listGankHttpResponse.getResults().size());
                        mView.showDataSuccess(listGankHttpResponse.getResults());
                    }else{
                        mView.showDataError("网络异常", ResultTag.NO_DATA);
                    }
                }else{
                    mView.showDataError("网络异常", ResultTag.ERROR);
                }
            }
        }));
    }



}

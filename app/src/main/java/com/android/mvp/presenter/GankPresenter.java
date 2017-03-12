package com.android.mvp.presenter;

import com.android.mvp.base.BasePresenter;
import com.android.mvp.bean.GankHttpResponse;
import com.android.mvp.bean.GankItemBean;
import com.android.mvp.http.ResultTag;
import com.android.mvp.model.GankModel;
import com.android.mvp.presenter.contract.GankContract;
import com.android.mvp.util.MLog;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
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


        addSubscription(mModel.getGirlList(pageSize,pageIndex).subscribe(new Subscriber<GankHttpResponse<List<GankItemBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showDataError("网络异常", ResultTag.ERROR);
            }

            @Override
            public void onNext(GankHttpResponse<List<GankItemBean>> listGankHttpResponse) {

                if(!listGankHttpResponse.getError()){
                    mView.showDataSuccess(listGankHttpResponse.getResults());
                }else{
                    mView.showDataError("网络异常", ResultTag.ERROR);
                }
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

package com.android.mvp.presenter;

import com.android.mvp.base.BasePresenter;
import com.android.mvp.bean.WYDetial;
import com.android.mvp.model.NewModel;
import com.android.mvp.presenter.contract.NewDetialContract;

import java.util.Map;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/10/31.
 */

public class NewDetialPresenter extends BasePresenter<NewDetialContract.View> implements NewDetialContract.Presenter {


    private NewModel model;


    public NewDetialPresenter() {

        this.model = new NewModel();
    }

    @Override
    public void getNewById(String id) {

        model.getNewsDetail(id).subscribe(new Subscriber<Map<String, WYDetial>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showDataError("网络异常",0);
            }

            @Override
            public void onNext(Map<String, WYDetial> stringWYDetialMap) {
                WYDetial newsDetail = stringWYDetialMap.get(id);
                mView.showDataSuccess(newsDetail);
            }
        });
    }
}

package com.android.mvp.presenter;

import com.android.mvp.base.BasePresenter;
import com.android.mvp.bean.WXHttpResponse;
import com.android.mvp.bean.WXItemBean;
import com.android.mvp.http.ResultTag;
import com.android.mvp.http.WeChatApis;
import com.android.mvp.model.WXModel;
import com.android.mvp.presenter.contract.WxContract;
import com.android.mvp.util.MLog;

import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/20  13:30
 */
public class WxPresenter extends BasePresenter<WxContract.View> implements  WxContract.Presenter  {


    private WXModel mModel;


    public WxPresenter() {
        mModel=new WXModel();
    }

    @Override
    public void getWxHost(Map<String, String> map) {
        map.put("key", WeChatApis.MYKEY);
        addSubscription(mModel.getWXHot(map).subscribe(new Subscriber<WXHttpResponse<List<WXItemBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showDataError("网络异常", ResultTag.ERROR);
            }

            @Override
            public void onNext(WXHttpResponse<List<WXItemBean>> listWXHttpResponse) {
                MLog.v("DaLong",listWXHttpResponse.getMsg()+"        "+listWXHttpResponse.getCode());
                if(listWXHttpResponse.getCode()==200){
                    mView.showDataSuccess(listWXHttpResponse.getNewslist());
                }else{
                    mView.showDataError(listWXHttpResponse.getMsg(),listWXHttpResponse.getCode());
                }
            }
        }));
    }

    @Override
    public void getWxHostSearch(Map<String, String> map) {
        map.put("key", WeChatApis.MYKEY);
        addSubscription(mModel.getWXHotSearch(map).subscribe(new Subscriber<WXHttpResponse<List<WXItemBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showDataError("网络异常", ResultTag.ERROR);
            }

            @Override
            public void onNext(WXHttpResponse<List<WXItemBean>> listWXHttpResponse) {
                MLog.v("DaLong",listWXHttpResponse.getMsg()+"        "+listWXHttpResponse.getCode());
                if(listWXHttpResponse.getCode()==200){
                    mView.showDataSuccess(listWXHttpResponse.getNewslist());
                }else{
                    mView.showDataError(listWXHttpResponse.getMsg(),listWXHttpResponse.getCode());
                }
            }
        }));
    }
}

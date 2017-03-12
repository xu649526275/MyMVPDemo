package com.android.mvp.model;

import com.android.mvp.base.BaseModel;
import com.android.mvp.bean.WXHttpResponse;
import com.android.mvp.bean.WXItemBean;
import com.android.mvp.http.Http;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/20  13:24
 */
public class WXModel  extends BaseModel{


    /**
     * 微信精选
     * */
    public Observable<WXHttpResponse<List<WXItemBean>>> getWXHot(Map<String,String> map){
        return Http.getInstance().getWXService().getWXHot(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 微信精选搜索
     * */
    public Observable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(Map<String,String> map){
        return Http.getInstance().getWXService().getWXHotSearch(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}

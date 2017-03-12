package com.android.mvp.model;

import com.android.mvp.bean.GankHttpResponse;
import com.android.mvp.bean.GankItemBean;
import com.android.mvp.http.Http;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/21  14:37
 */
public class GankModel {


    /**
     * 获取妹子图片
     * */
    public Observable<GankHttpResponse<List<GankItemBean>>> getGirlList( int num,int page){
        return Http.getInstance().getGankService().getGirlList(num,page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 获取技术文章
     * */
    public Observable<GankHttpResponse<List<GankItemBean>>> getTechList(String tech, int num,int page){
        return Http.getInstance().getGankService().getTechList(tech,num,page);
    }
}

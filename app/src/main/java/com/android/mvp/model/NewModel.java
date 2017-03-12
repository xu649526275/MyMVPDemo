package com.android.mvp.model;

import com.android.mvp.bean.WYDetial;
import com.android.mvp.bean.WYNewsBean;
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
 * @time 2016/10/10  14:58
 */
public class NewModel {


    /**
     * 获取新闻List列表
     * */
    public Observable<Map<String, List<WYNewsBean>>> getNewsList(String type, String id, int pageIndex){
        return Http.getInstance().getNewService().getNewsList(type,id,pageIndex ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<Map<String,WYDetial>> getNewsDetail(String id){

        return Http.getInstance().getNewService().getNewsDetail(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}

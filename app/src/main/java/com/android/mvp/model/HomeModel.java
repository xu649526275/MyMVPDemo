package com.android.mvp.model;

import com.android.mvp.base.BaseModel;
import com.android.mvp.bean.BrandBean;
import com.android.mvp.bean.HomeBanner;
import com.android.mvp.bean.HomePost;
import com.android.mvp.bean.HomeSpecial;
import com.android.mvp.bean.M_Base;
import com.android.mvp.http.Http;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/9/7 0007.
 */
public class HomeModel extends BaseModel {




    public Observable<M_Base<List<HomeBanner>>> getHomeBanner() {
        return Http.getInstance().getSAYDService().homeBanner().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }



    /**
     * 首页
     * */
    public Observable<M_Base<List<BrandBean>>> homeBrand(){

        return Http.getInstance().getSAYDService().homeBrand();
    }


    /**
     * 热帖
     * */
    public Observable<M_Base<List<HomePost>>> homePost(){
        return Http.getInstance().getSAYDService().homePost();
    }

    public Observable<M_Base<List<HomeSpecial>>> homeSpecial(Map<String,String> map){
        return Http.getInstance().getSAYDService().homeSpecial(map);
    }




}

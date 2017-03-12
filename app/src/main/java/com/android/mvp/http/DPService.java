package com.android.mvp.http;


import com.android.mvp.bean.BrandBean;
import com.android.mvp.bean.HomeBanner;
import com.android.mvp.bean.HomePost;
import com.android.mvp.bean.HomeSpecial;
import com.android.mvp.bean.M_Base;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * Created by dalong on 2016/3/16.
 */
public interface DPService {


    /**
     * 首页Banner
     * */
    @GET(ConstantValues.Home_Banner)
    Observable<M_Base<List<HomeBanner>>> homeBanner();


    /**
     * 首页
     * */
    @GET(ConstantValues.Home_ProductBrand)
    Observable<M_Base<List<BrandBean>>> homeBrand();

    /**
     * 热帖
     * */
    @GET(ConstantValues.HOME_Posts)
    Observable<M_Base<List<HomePost>>> homePost();


    /**
     * 专题热门
     * */
    @GET(ConstantValues.Home_SpecialTopic)
    Observable<M_Base<List<HomeSpecial>>> homeSpecial(@QueryMap Map<String,String> map);


}

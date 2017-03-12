package com.android.mvp.http;

import com.android.mvp.bean.WXHttpResponse;
import com.android.mvp.bean.WXItemBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by codeest on 16/8/28.
 */

public interface WeChatApis {

    String HOST = "http://api.tianapi.com/";
    String MYKEY="a168a48492d240fae6fd2c6b6ea4e7b4";
//
    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> getWXHot(@Query("key") String key, @Query("num") int num, @Query("page") int page);
      @GET("wxnew")
      Observable<WXHttpResponse<List<WXItemBean>>> getWXHot(@QueryMap Map<String,String> map);
    /**
     * 微信精选列表
     */
    @GET("wxnew")
    Observable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(@Query("key") String key, @Query("num") int num, @Query("page") int page, @Query("word") String word);
      @GET("wxnew")
      Observable<WXHttpResponse<List<WXItemBean>>> getWXHotSearch(@QueryMap Map<String,String> map);
}

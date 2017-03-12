package com.android.mvp.http;

import com.android.mvp.bean.WYDetial;
import com.android.mvp.bean.WYNewsBean;
import com.android.mvp.bean.WYVideosBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import rx.Observable;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/22  15:52
 */
public interface WYApis {

    /**
     * 请求新闻列表 例子：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     *
     * @param type      新闻类别：headline为头条,local为北京本地,fangchan为房产,list为其他
     * @param id        新闻类别id
     * @param startPage 开始的页码
     * @return 被观察对象
     */
    @GET(WyConstantValues.HEAD+"{type}/{id}/{pageIndex}-20.html")
    Observable<Map<String, List<WYNewsBean>>> getNewsList(
            @Path("type") String type, @Path("id") String id,@Path("pageIndex") int pageIndex);

    /**
     * 新闻详情：例子：http://c.m.163.com/nc/article/BFNFMVO800034JAU/full.html
     *
     * @param postId 新闻详情的id
     * @return 被观察对象
     * @Header("Cache-Control") String cacheControl,
     */
    @GET("nc/article/{postId}/full.html")
    Observable<Map<String, WYDetial>> getNewsDetail(@Path("postId") String postId);


    /**
     * 网易视频列表 例子：http://c.m.163.com/nc/video/list/V9LG4B3A0/n/0-10.html
     *
     * @param id        视频类别id
     * @param startPage 开始的页码
     * @return 被观察者
     */
    @GET("nc/video/list/{id}/n/{pageIndex}-10.html")
    Observable<Map<String, List<WYVideosBean>>> getVideoList(
      @Path("id") String id,@Path("pageIndex") int pageIndex);

}

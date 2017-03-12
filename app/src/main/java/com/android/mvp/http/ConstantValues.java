package com.android.mvp.http;

/**
 * 点屏app Service
 *
 * @autor 徐文龙
 * @time 2016/7/14  15:10
 */
public class ConstantValues {

    public static boolean ISUPLOAD = false;
    public static final String packageName = "com.android.mvp";
    public static final String MAIN_HTML = "http://www.shiandianping.com";




    // 正式服务器
    public static final String URL_HEAD = "http://api.shiandianping.com";

    /**
     * 请求来源
     */
    public static final String URL_SOURCE = "sourceAndroidDianPing";

    /**
     * URL
     */
    public static final String BASE_URL = "/api/" + URL_SOURCE + "/v1.0/";


    /**
     * 首页
     * */

    //首页轮播图
    public static final String Home_Banner=BASE_URL+"Banner";

    //首页产品类别
    public static final String Home_ProductBrand=BASE_URL+"ProductBrand";

    //首页社区
    public static final String HOME_Posts=BASE_URL+"posts/GetHeatPost";

    //首页专题
    public static final String Home_SpecialTopic=BASE_URL+"SpecialTopic";
    //专题详情
    public static final String SpecialInfo=BASE_URL+"SpecialTopic";


}

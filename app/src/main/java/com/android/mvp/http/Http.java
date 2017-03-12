package com.android.mvp.http;

import com.android.mvp.ui.MyApplication;
import com.android.mvp.util.MLog;
import com.android.mvp.util.NetUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



public class Http {

    private static Http ourInstance;
    private static Gson gson;
    private Retrofit retrofit;
    private DPService saydService;
    private WeChatApis wxService;
    private GankApis gankService;
    private WYApis newService;
    private OkHttpClient sOkHttpClient;

    public static Http getInstance() {
        gson = new GsonBuilder().create();
        if (ourInstance == null) ourInstance = new Http();
        return ourInstance;
    }



    /**
     * 设缓存有效期为两天
     */
    public static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    public static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    public static final String CACHE_CONTROL_AGE = "max-age=0";


    private Http() {

        // 注解初始化
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantValues.URL_HEAD) // 设置url头  ：http:www.shianyunduan.com
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //设置返回类型为RXJava，或者回调
                .addConverterFactory(GsonConverterFactory.create(gson))  //设置返回数据类型
                .client(getOkHttpClient())  //设置网络请求，默认httpclient
                .build();

        // 绑定service
        this.saydService = retrofit.create(DPService.class);

        // 注解初始化
        Retrofit retrofitWx = new Retrofit.Builder()
                .baseUrl(WeChatApis.HOST) // 设置url头  ：http:www.shianyunduan.com
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //设置返回类型为RXJava，或者回调
                .addConverterFactory(GsonConverterFactory.create(gson))  //设置返回数据类型
                .client(getOkHttpClient())  //设置网络请求，默认httpclient
                .build();

        // 绑定service
        this.wxService = retrofitWx.create(WeChatApis.class);

        // 注解初始化
        Retrofit retrofitGank = new Retrofit.Builder()
                .baseUrl(GankApis.HOST) // 设置url头  ：http:www.shianyunduan.com
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //设置返回类型为RXJava，或者回调
                .addConverterFactory(GsonConverterFactory.create(gson))  //设置返回数据类型
                .client(getOkHttpClient())  //设置网络请求，默认httpclient
                .build();

        // 绑定service
        this.gankService = retrofitGank.create(GankApis.class);



        // 注解初始化
        Retrofit newsRefit = new Retrofit.Builder()
                .baseUrl(WyConstantValues.NETEAST_HOST) // 设置url头  ：http:www.shianyunduan.com
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //设置返回类型为RXJava，或者回调
                .addConverterFactory(GsonConverterFactory.create(gson))  //设置返回数据类型
                .client(getOkHttpClient())  //设置网络请求，默认httpclient
                .build();

        // 绑定service
        this.newService = newsRefit.create(WYApis.class);

    }




    public WYApis getNewService() {
        return newService;
    }



    public DPService getSAYDService() {
        return saydService;
    }

    public WeChatApis getWXService() {
        return wxService;
    }

    public GankApis getGankService() {
        return gankService;
    }

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (Http.class) {
                Cache cache = new Cache(new File(MyApplication.getAppContext().getCacheDir(), "HttpCache"),
                        1024 * 1024 * 100);
                if (sOkHttpClient == null) {
                    sOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .connectTimeout(6, TimeUnit.SECONDS)
                            .readTimeout(6, TimeUnit.SECONDS)
                            .writeTimeout(6, TimeUnit.SECONDS)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor).build();
                }
            }
        }
        return sOkHttpClient;
    }


    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                MLog.d("no network");
            }
            Response originalResponse = chain.proceed(request);
            if (NetUtil.isNetworkAvailable()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            MLog.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            MLog.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };


}

package com.android.mvp.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.android.mvp.BuildConfig;
import com.android.mvp.R;
import com.android.mvp.ui.main.MainActivity;
import com.android.mvp.util.MLog;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.smtt.sdk.QbSdk;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * created by 大龙, 16/04/09
 * Copyright (c) 2016, xu649526275@gmail.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG            #
 * #                                                   #
 */


public class   MyApplication extends Application {
    private static Context sAppContext;
    private static MyApplication instance;
    private List<Activity> activityList = new LinkedList<Activity>();
    private static final String TAG = "MyApplication";
    private static final String RealmName="dalong.realm";
    // 单例模式中获取唯一的MyApplication实例
    public static  MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }
    /**
     * 内存泄漏
     * */
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    public MyApplication(){
        sAppContext=getAppContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        MLog.init(true);
//        initCallgraphy();
        sAppContext=this;
        initLeakCanary();


        initOkhttp();




        //初始化tbs x5 webview
//        QbSdk.allowThirdPartyAppDownload(true);
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {

            }
        });
//        QbSdk.initX5Environment(getApplicationContext(), QbSdk.WebviewInitType.FIRSTUSE_AND_PRELOAD, new QbSdk.PreInitCallback() {
//            @Override
//            public void onCoreInitFinished() {
//            }
//
//            @Override
//            public void onViewInitFinished(boolean b) {
//            }
//        });






    }



    public static Context getAppContext() {
        return sAppContext;
    }

    /**
     * 全局变量字体
     * */
//    private void initCallgraphy() {
//
//
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/ac.ttf")
//                .setFontAttrId(R.attr.fontPath)
////                .addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport.class)
////                .addCustomStyle(TextField.class, R.attr.textFieldStyle)
//                .build()
//        );
//    }

    private void initOkhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

    }







    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            refWatcher = LeakCanary.install(this);
        } else {
            refWatcher = installLeakCanary();
        }
    }
    /**
     * release版本使用此方法
     */
    protected RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }















    // 遍历所有Activity并finish
    public void exitNoMain() {
        for (Activity activity : activityList) {
            if (activity instanceof MainActivity) {

            }else {
                activity.finish();
            }
        }
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
        MLog.v(TAG,"添加数据了");
    }

    // 添加Activity到容器中
    public void clearActivity() {
        activityList.clear();
    }
}

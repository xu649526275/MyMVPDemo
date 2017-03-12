package com.android.mvp.ui.wx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.android.mvp.R;
import com.android.mvp.base.BaseActivity;
import com.android.mvp.util.MLog;
import com.android.mvp.widget.view.MyToolbar;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * 微信内容详情，使用腾讯x5浏览器
 * Created by codeest on 16/8/20.
 */

public class WxWebActivity extends BaseActivity {



    String title, url;
    @Bind(R.id.wv_tech_content)
    WebView wvTechContent;
    @Bind(R.id.mytoolbar)
    MyToolbar mMyToolbar;
    MyToolbar.Builder mBuilder;


    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_web;
    }

    @Override
    protected void initToolbar() {
        mBuilder=new MyToolbar.Builder(mMyToolbar);
        if(title!=null){
            mBuilder.setTitle(title);
        }
        mBuilder.setLeftImg(R.mipmap.ic_arrow_back).setLeftImgListerner(v -> finish());

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        title = intent.getExtras().getString("title");
        url = intent.getExtras().getString("url");

        MLog.v("DaLong",url);
        initEventAndData();

    }


    protected void initEventAndData() {

        WebSettings settings = wvTechContent.getSettings();
//        if (SharedPreferenceUtil.getNoImageState()) {
//            settings.setBlockNetworkImage(true);//设置后webview会不显示图片
//        }
        //if (SharedPreferenceUtil.getAutoCacheState()) {//自动缓存功能



//            settings.setAppCacheEnabled(true);
//            settings.setDomStorageEnabled(true);
//            settings.setDatabaseEnabled(true);
//            if (NetUtil.isNetworkAvailable()) {
//                settings.setCacheMode(WebSettings.LOAD_DEFAULT);
//            } else {
//                settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
//            }


        //}
        settings.setJavaScriptEnabled(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setSupportZoom(true);


        wvTechContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
//                view.loadDataWithBaseURL(null,"","text/html","utf-8",null);
                return true;
            }
        });
//        wvTechContent.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                super.onProgressChanged(view, newProgress);
//                if (newProgress == 100) {
//                    //viewLoading.stop();
//                } else {
////                    if (!viewLoading.isStart()) {
////                        viewLoading.start();
////                    }
//                }
//            }
//
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                super.onReceivedTitle(view, title);
//                setTitle(title);
//            }
//        });
        wvTechContent.loadUrl(url);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wvTechContent.canGoBack()) {
            wvTechContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        wvTechContent.destroy();
        super.onDestroy();
    }

    //    @Override
//    public void onBackPressedSupport() {
//        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
//            pop();
//        } else {
//            finishAfterTransition();
//        }
//    }
}

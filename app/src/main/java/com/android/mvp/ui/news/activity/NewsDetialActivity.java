package com.android.mvp.ui.news.activity;

import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.base.BaseActivity;
import com.android.mvp.bean.WYDetial;
import com.android.mvp.presenter.NewDetialPresenter;
import com.android.mvp.presenter.contract.NewDetialContract;
import com.android.mvp.util.DateUtil;
import com.android.mvp.util.MLog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.List;

import butterknife.Bind;

/**
 *
 * 新闻详情，就做了一个目前
 * Created by Administrator on 2016/10/31.
 */

public class NewsDetialActivity extends BaseActivity<NewDetialPresenter, WYDetial> implements NewDetialContract.View {


    @Bind(R.id.news_detail_photo_iv)
    ImageView newsDetailPhotoIv;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;
    @Bind(R.id.news_detail_from_tv)
    TextView newsDetailFromTv;
    @Bind(R.id.news_detail_body_tv)
    WebView newsDetailBodyTv;
    private String postId;
    private String newsSource;

    @Bind(R.id.my_view)
    LinearLayout myView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_detial;
    }

    @Override
    protected void initToolbar() {
        toolbarLayout.setTitle("哈哈哈");
        toolbar.setNavigationOnClickListener(v -> finish());
//        toolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white));
//        toolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
    }

    @Override
    protected void initView() {
        mPresenter.attachView(this, this);
        postId = getIntent().getStringExtra("postId");
        MLog.v("DaLong",postId);

        showLoadingView();
    }

    @Override
    protected void initData() {
        mPresenter.getNewById(postId);
    }

    @Override
    public NewDetialPresenter getPresenter() {
        return new NewDetialPresenter();

    }


    @Override
    public void showDataSuccess(WYDetial datas) {
        super.showDataSuccess(datas);
        showContent();
        if(datas!=null){
//            toolbarLayout.setTitle(datas.getTitle());
            Glide.with(this).load(getImgSrcs(datas)).asBitmap()
                    .placeholder(R.mipmap.defalt_img)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .error(R.mipmap.defalt_img)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(newsDetailPhotoIv);



            String newsTime = DateUtil.formatDate(datas.getPtime());//时间转换不要年
            newsSource = datas.getSource();
            newsDetailFromTv.setText(getString(R.string.news_from, newsSource, newsTime));

            
            setBodey(datas);//设置内容
            
        }
    }

    @Override
    public void showDataError(String errorMessage, int tag) {
        super.showDataError(errorMessage, tag);
//        showNetErrorView();
    }

    @Override
    public void onReload() {
        super.onReload();
        mPresenter.getNewById(postId);
    }

    @Override
    public View getLoadingTargetView() {
        return myView;
    }



    /**
     * 初始化头部图片
     * */
    private String getImgSrcs(WYDetial newsDetail) {
        List<WYDetial.ImgBean> imgSrcs = newsDetail.getImg();
        String imgSrc;
        if (imgSrcs != null && imgSrcs.size() > 0) {
            imgSrc = imgSrcs.get(0).getSrc();
        } else {
            imgSrc = getIntent().getStringExtra("img");
        }
        return imgSrc;
    }


    /**
     * 将内容<!--IMG#1-->类似的内容转换成html的img  用html加载</>
     * */
    public void setBodey(WYDetial bodey) {
        int imgSize=bodey.getImg().size();
        String content=bodey.getBody().toString();
        for(int i=0;i<imgSize;i++){
            String ImgDefPath="<!--IMG#"+(i)+"-->";
            content=content.replace(ImgDefPath,"<img style='width:100%;' src='"+bodey.getImg().get(i).getSrc()+"' />");
        }

        WebSettings settings = newsDetailBodyTv.getSettings();
        settings.setJavaScriptEnabled(true);
        newsDetailBodyTv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
//                view.loadDataWithBaseURL(null,"","text/html","utf-8",null);
                return true;
            }
        });
        newsDetailBodyTv.loadDataWithBaseURL(null,content,"text/html","utf-8",null);
       // newsDetailBodyTv.setText(Html.fromHtml(content));
    }

}

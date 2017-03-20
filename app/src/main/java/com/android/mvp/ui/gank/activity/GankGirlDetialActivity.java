package com.android.mvp.ui.gank.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.base.BaseActivity;
import com.android.mvp.util.GeneralUtil;
import com.android.mvp.util.ImageLoader;
import com.android.mvp.util.SystemUiVisibilityUtil;
import com.android.mvp.widget.view.MyToolbar;
import com.android.mvp.widget.view.ProgressWheel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 干货，美女图片详情
 * Created by Administrator on 2016/10/14.
 */

public class GankGirlDetialActivity extends BaseActivity {


    @Bind(R.id.photo_view)
    PhotoView photoView;

    @Bind(R.id.my_toolbar)
    MyToolbar toolbar;

    @Bind(R.id.progress_img)
    ProgressWheel progressImg;
    @Bind(R.id.tv_reload)
    TextView tvReload;

    private String url;


    private boolean mIsToolBarHidden;
    private boolean mIsStatusBarHidden;

    @Override
    public int getLayoutId() {
        return R.layout.activity_gank_girl;
    }

    @Override
    protected void initToolbar() {
        new MyToolbar.Builder(toolbar).setLeftImg(R.mipmap.ic_arrow_back).setTitle("图片").setLeftImgListerner(v -> finish());
        toolbar.setBackgroundColor(Color.parseColor("#00000000"));
//        setTranslucentStatus(getResources().getColor(R.color.transparent));
    }

    @Override
    protected void initView() {
        url = getIntent().getStringExtra("url");
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {

//
//                hideOrShowToolbar();
//                hideOrShowStatusBar();
            }

            @Override
            public void onOutsidePhotoTap() {
//                hideOrShowToolbar();
//                hideOrShowStatusBar();
            }
        });

        tvReload.setOnClickListener(v -> {
            initData();
        });
    }

    @Override
    protected void initData() {
        if (!GeneralUtil.isEmpty(url)) {
//            Picasso.with(context).load(url).placeholder(R.color.content_color).into(photoView);
            Glide.with(context).load(url).placeholder(R.mipmap.defalt_img).fitCenter().dontAnimate().listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    progressImg.setVisibility(View.GONE);
                    tvReload.setVisibility(View.VISIBLE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    photoView.setImageDrawable(resource);
                    progressImg.setVisibility(View.GONE);
                    tvReload.setVisibility(View.GONE);
                    return true;
                }
            }).into(photoView);
        }

    }


    protected void hideOrShowToolbar() {
        toolbar.animate()
                .alpha(mIsToolBarHidden ? 1.0f : 0.0f)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsToolBarHidden = !mIsToolBarHidden;
    }

    private void hideOrShowStatusBar() {
        if (mIsStatusBarHidden) {
            SystemUiVisibilityUtil.enter(GankGirlDetialActivity.this);
        } else {
            SystemUiVisibilityUtil.exit(GankGirlDetialActivity.this);
        }
        mIsStatusBarHidden = !mIsStatusBarHidden;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

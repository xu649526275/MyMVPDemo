package com.android.mvp.ui.gank.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.android.mvp.R;
import com.android.mvp.base.BaseActivity;
import com.android.mvp.util.GeneralUtil;
import com.android.mvp.util.MLog;
import com.android.mvp.util.SystemUiVisibilityUtil;
import com.android.mvp.widget.view.MyToolbar;
import com.squareup.picasso.Picasso;

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
        setTranslucentStatus(getResources().getColor(R.color.transparent));
    }

    @Override
    protected void initView() {
        url=getIntent().getStringExtra("url");
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
    }

    @Override
    protected void initData() {
        if(!GeneralUtil.isEmpty(url)){
            Picasso.with(context).load(url).placeholder(R.color.content_color).into(photoView);
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


}

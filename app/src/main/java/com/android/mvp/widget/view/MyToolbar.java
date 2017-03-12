package com.android.mvp.widget.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.mvp.R;
import com.android.mvp.util.GeneralUtil;

/**
 * Created by mushuai on 2016/7/15.
 */
public class MyToolbar extends Toolbar {

    private TextView mTitleContent;
    private TextView mLeftTv;
    private TextView mRightTv;
    private ImageView mRightImg;
    private RelativeLayout mMessageView;
    private ImageView mUnReadImg;

    public MyToolbar(Context context) {
        super(context);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        // TODO Auto-generated method stub
        super.onFinishInflate();
        mTitleContent = (TextView) findViewById(R.id.title_content);
        mLeftTv = (TextView) findViewById(R.id.title_left_tv);
        mRightTv = (TextView) findViewById(R.id.title_right_tv);
        mRightImg = (ImageView) findViewById(R.id.title_right_img);
        mMessageView = (RelativeLayout) findViewById(R.id.toolbar_message_view);
        mUnReadImg = (ImageView) findViewById(R.id.toolbar_message_unread_img);
    }


    public static class Builder {

        MyToolbar toolbar;

        public Builder(MyToolbar toolbar) {
            this.toolbar = toolbar;
        }

        public Builder setTitle(String title) {

            if (!GeneralUtil.isEmpty(title)) {
                toolbar.mTitleContent.setVisibility(View.VISIBLE);
                toolbar.mTitleContent.setText(title);
            }
            return this;
        }

        public Builder setLeftTv(String leftstr) {

            if (!GeneralUtil.isEmpty(leftstr)) {
                toolbar.mLeftTv.setVisibility(View.VISIBLE);
                toolbar.mLeftTv.setText(leftstr);
            }
            return this;
        }

        public Builder setLeftTvListener(OnClickListener onClickListener) {
            toolbar.mLeftTv.setOnClickListener(onClickListener);
            return this;
        }

        public Builder setRightTv(String rightstr) {

            if (!GeneralUtil.isEmpty(rightstr)) {
                toolbar.mRightTv.setVisibility(View.VISIBLE);
                toolbar.mRightTv.setText(rightstr);
            }
            return this;
        }
        public Builder setRightTvColor(int color) {
                toolbar.mRightTv.setTextColor(color);
            return this;
        }

        public Builder setRightTvListener(OnClickListener onClickListener) {
            toolbar.mRightTv.setOnClickListener(onClickListener);
            return this;
        }

        public Builder setRightImg(int resoulce) {
            toolbar.mRightImg.setVisibility(View.VISIBLE);
            toolbar.mRightImg.setImageResource(resoulce);
            return this;
        }

        public Builder setRightImgListener(OnClickListener onClickListener) {
            toolbar.mRightImg.setOnClickListener(onClickListener);
            return this;
        }


        public Builder setLeftImg(int resoulce) {
            toolbar.setNavigationIcon(resoulce);
            return this;
        }

        public Builder setLeftImgListerner(OnClickListener onClickListener) {
            toolbar.setNavigationOnClickListener(onClickListener);
            return this;
        }

        public Builder setMessageView(OnClickListener onClickListener) {
            toolbar.mMessageView.setVisibility(View.VISIBLE);
            toolbar.mMessageView.setOnClickListener(onClickListener);
            return this;
        }

        public Builder showUnReadMessageImg(boolean flag) {
            if(flag){
                toolbar.mUnReadImg.setVisibility(View.VISIBLE);
            }else{
                toolbar.mUnReadImg.setVisibility(View.GONE);
            }

            return this;
        }


        public MyToolbar create() {
            return toolbar;
        }
    }

}

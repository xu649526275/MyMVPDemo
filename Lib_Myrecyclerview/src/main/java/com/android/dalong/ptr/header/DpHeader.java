package com.android.dalong.ptr.header;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.android.dalong.R;
import com.android.dalong.ptr.PtrFrameLayout;
import com.android.dalong.ptr.PtrUIHandler;
import com.android.dalong.ptr.indicator.PtrIndicator;

/**
 * Created by mushuai on 2016/8/1.
 */
public class DpHeader extends LinearLayout implements PtrUIHandler {
    public DpHeader(Context context) {
        super(context);
        initView();
    }

    public DpHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView(){

        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.dp_header, this);

    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }
}

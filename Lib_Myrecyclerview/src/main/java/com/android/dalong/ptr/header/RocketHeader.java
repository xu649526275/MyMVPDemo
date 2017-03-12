package com.android.dalong.ptr.header;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.android.dalong.R;
import com.android.dalong.ptr.PtrFrameLayout;
import com.android.dalong.ptr.PtrUIHandler;
import com.android.dalong.ptr.indicator.PtrIndicator;

/**
 * Created by mushuai on 2016/3/22.
 */
public class RocketHeader extends LinearLayout implements PtrUIHandler {

    public RocketHeader(Context context) {
        super(context);
        initView();
    }

    public RocketHeader(Context context, AttributeSet attrs) {
        super(context);
    }

    public RocketHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
    }

    public RocketHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs);
    }

    private void initView(){


        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.rocket_header, this);

    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {

        Log.i("mus", "onUIReset");

    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

        Log.i("mus", "onUIRefreshPrepare");

    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {

        Log.i("mus", "onUIRefreshBegin");

    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

        Log.i("mus", "onUIRefreshComplete");

    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

        Log.i("mus", "onUIPositionChange   " + isUnderTouch + "    status: " + status + "  Y:" + ptrIndicator.getCurrentPosY() + "   height: " + frame.getHeaderHeight());
    }

}

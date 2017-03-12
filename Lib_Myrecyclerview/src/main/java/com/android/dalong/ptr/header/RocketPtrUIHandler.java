package com.android.dalong.ptr.header;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.dalong.R;
import com.android.dalong.ptr.PtrFrameLayout;
import com.android.dalong.ptr.PtrUIHandler;
import com.android.dalong.ptr.indicator.PtrIndicator;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/3/30  17:35
 */
public  class RocketPtrUIHandler extends FrameLayout implements PtrUIHandler {

    private ImageView img;
    private ProgressBar progressBar;
    private TextView title;

    public RocketPtrUIHandler(Context context) {
        super(context);
        initView();
    }



    public RocketPtrUIHandler(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    protected  void initView(){

        View view= LayoutInflater.from(getContext()).inflate(R.layout.rocket_header,this);
        title= (TextView) view.findViewById(R.id.ref_title);
        img= (ImageView) view.findViewById(R.id.ref_img);
        progressBar= (ProgressBar) view.findViewById(R.id.ref_progress);
        progressBar.setVisibility(View.INVISIBLE);
        title.setText("下拉刷新");
    }




    @Override
    public void onUIReset(PtrFrameLayout frame) {
        //Content 重新回到顶部， Header 消失，整个下拉刷新过程完全结束以后，重置 View 。
   //     Log.v("dalongs1","重置");
        title.setText("下拉刷新");
        title.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        img.setVisibility(View.VISIBLE);
        img.setAlpha(0f);
        img.setScaleX(0.0f);
        img.setScaleY(0.0f);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        //准备刷新，Header 将要出现时调用。
    //    Log.v("dalongs1","将要下拉刷新");
        title.setText("下拉刷新");
        progressBar.setVisibility(View.INVISIBLE);
        img.setVisibility(View.VISIBLE);
        img.setScaleX(0.0f);
        img.setScaleY(0.0f);
        img.setAlpha(0f);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        //开始刷新，Header 进入刷新状态之前调用。
    //    Log.v("dalongs1","刷新中");
        progressBar.setVisibility(View.VISIBLE);
        title.setText("加载中...");
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        //刷新结束，Header 开始向上移动之前调用。
    //    Log.v("dalongs1","刷新完成");
        progressBar.setVisibility(View.VISIBLE);
        title.setText("更新完成");
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        //下拉过程中位置变化回调。
        final int mOffsetToRefresh = frame.getOffsetToRefresh();//规定高度
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

        float scale;
        scale=(float)lastPos/(float)mOffsetToRefresh;

        if(scale<=1){
            img.setScaleX(scale);
            img.setScaleY(scale);
            img.setAlpha(scale);
        }else{
            img.setScaleX(1.0f);
            img.setScaleY(1.0f);
            img.setAlpha(1.0f);
        }

        int train = (int) (scale * mOffsetToRefresh);
      //  Log.v("dalongs", "" + scale+"    "+lastPos+"   "+mOffsetToRefresh +"    "+train);
        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                //下拉
                if (!frame.isPullToRefresh()) {
                    title.setText("下拉刷新");
                }
            }

        }else  if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                //上拉刷新
                if (!frame.isPullToRefresh()) {
                    title.setText("释放刷新");

                }
            }

        }


    }
}

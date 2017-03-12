package com.android.dalong;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.android.dalong.ptr.PtrClassicFrameLayout;
import com.android.dalong.ptr.PtrDefaultHandler;
import com.android.dalong.ptr.PtrFrameLayout;
import com.android.dalong.ptr.PtrHandler;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class MyRefLayout  extends PtrClassicFrameLayout{


    public MyRefLayout(Context context) {
        super(context);
    }

    public MyRefLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }




    public void initDefRefresh(){
        this.setLoadingMinTime(500);
        this.setPullToRefresh(false);
        this.setKeepHeaderWhenRefresh(true);
//        this.autoRefresh(false);第一次下拉是否自动刷新
        this.disableWhenHorizontalMove(true);
//        this.setEnabledNextPtrAtOnce(true);
    }


    public void setRefresh(final PtrRefresh ptrRefresh){
        this.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrRefresh.ptrrefresh(frame);
            }
        });
    }





    public interface PtrRefresh{
        public void ptrrefresh(PtrFrameLayout frame);
    }


}

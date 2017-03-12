package com.android.mvp.presenter.contract;

import com.android.mvp.base.BaseViewI;
import com.android.mvp.bean.GankItemBean;

import java.util.List;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/21  14:34
 */
public interface GankContract {

     interface View extends BaseViewI<List<GankItemBean>>{

    };


    interface Presenter{
        public void getGankGirl(int pageSize,int pageIndex);

        public void getGankTech(String tech,int pageSize,int pageIndex);
    }
}

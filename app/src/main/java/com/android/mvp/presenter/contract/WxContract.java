package com.android.mvp.presenter.contract;

import com.android.mvp.base.BaseViewI;
import com.android.mvp.bean.WXItemBean;

import java.util.List;
import java.util.Map;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/20  13:30
 */
public interface WxContract {


    public interface  View extends BaseViewI<List<WXItemBean>>{

    }

    public interface Presenter{

        public void getWxHost(Map<String,String> map);

        public void getWxHostSearch(Map<String,String> map);
    }
}

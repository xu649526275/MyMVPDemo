package com.android.mvp.presenter.contract;

import com.android.mvp.base.BaseViewI;
import com.android.mvp.bean.WYDetial;

/**
 * Created by Administrator on 2016/10/31.
 */

public interface NewDetialContract {


    public  interface View extends BaseViewI<WYDetial>{}

    public interface Presenter{

        public void getNewById(String id);
    }
}

package com.android.mvp.ui.zhihu.fragment;

import android.view.View;

import com.android.mvp.R;
import com.android.mvp.base.BaseFragment;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/9/19  16:11
 */
public class FragmentZhihu extends BaseFragment {


    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View RootView) {

    }

    @Override
    protected void initToolbar() {

    }

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_zhihu;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView()
                    .setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}

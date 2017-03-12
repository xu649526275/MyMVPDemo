package com.android.mvp.ui.video;

import android.view.View;

import com.android.mvp.base.BaseFragment;

/**
 * Created by Administrator on 2016/9/29 0029.
 */

public class FragmentVideo extends BaseFragment {
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
        return 0;
    }


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView()
                    .setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }
}

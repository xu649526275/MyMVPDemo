package com.android.mvp.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.mvp.R;
import com.android.mvp.ui.gank.fragment.FragmentGank;
import com.android.mvp.ui.news.fragment.FragmentNew;
import com.android.mvp.ui.sayd.fragment.FragmentSAYDHome;
import com.android.mvp.ui.video.FragmentVideo;
import com.android.mvp.ui.wx.fragment.FragmentWx;
import com.android.mvp.ui.zhihu.fragment.FragmentZhihu;


public class FragmentFactory extends FragmentPagerAdapter {

    protected Context context;

    public FragmentFactory(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int res) {
        Fragment fragment = null;
        switch (res) {
//            case R.id.drawer_dp:
//                fragment=new FragmentSAYDHome();
//                break;
//            case R.id.drawer_zhihu:
//                fragment=new FragmentZhihu();
//                break;
            case R.id.drawer_gank:
                fragment=new FragmentGank();
                break;
            case R.id.drawer_wechat:
                fragment=new FragmentWx();
                break;
            case R.id.drawer_news:
                fragment=new FragmentNew();
                break;
            case R.id.drawer_video:
                fragment=new FragmentVideo();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

}

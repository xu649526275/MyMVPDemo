package com.android.mvp.ui.sayd.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * 首页banneradapter
 * */
public class HomeBannerAdapter extends PagerAdapter {

	private static final String TAG=HomeBannerAdapter.class.getSimpleName();

	private ArrayList<ImageView> viewlist;

	public HomeBannerAdapter(ArrayList<ImageView> viewlist) {
		this.viewlist = viewlist;
	}

	@Override
	public int getCount() {
		// 设置成最大，使用户看不到边界
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Warning：不要在这里调用removeView
		//container.removeView(viewlist.get(position%3));
	//	Log.v(TAG, "destroyItem" + position % 3);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// 对ViewPager页号求模取出View列表中要显示的项
		position %= viewlist.size();
		if (position < 0) {
			position = viewlist.size() + position%3;
		}
		ImageView view = viewlist.get(position%3 );


		// 如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
		ViewParent vp = view.getParent();
		if (vp != null) {
			ViewGroup parent = (ViewGroup) vp;
			parent.removeView(view);
		}
		container.addView(view);
		// add listeners here if necessary
		return view;
	}
}

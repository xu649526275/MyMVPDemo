package com.android.mvp.widget.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.utils.AutoLayoutHelper;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/7/18  12:01
 */
public class MyCardView extends CardView {

    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);
    public MyCardView(Context context) {
        super(context);
    }

    public MyCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public AutoFrameLayout.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new AutoFrameLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (!isInEditMode())
        {
            mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

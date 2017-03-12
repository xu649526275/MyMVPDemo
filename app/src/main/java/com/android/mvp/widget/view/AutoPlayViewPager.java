package com.android.mvp.widget.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * desc
 *
 * @autor 徐文龙
 * @time 2016/5/11  14:43
 */
public class AutoPlayViewPager extends ViewPager {

    private int showTime = 3 * 1000;
    /**
     * 滚动方向
     */
    private Direction direction = Direction.LEFT;

    /**
     * 设置播放时间，默认3秒
     *
     * @param showTimeMillis 毫秒
     */
    public void setShowTime(int showTimeMillis) {
        this.showTime = showTime;
    }


    public void setDirection(Direction direction){
        this.direction=direction;
    }



    public AutoPlayViewPager(Context context) {
        super(context);
    }

    public AutoPlayViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == SCROLL_STATE_IDLE)
                    start();
                else if (state == SCROLL_STATE_DRAGGING)
                    stop();
                 }
        });
    }


    /**
     * 开始
     */
    public void start() {
        // TODO 待实现开始播放
        stop();
        postDelayed(player, showTime);


    }

    /**
     * 停止
     */
    public void stop() {
        // TODO 待实现停止播放
        removeCallbacks(player);

    }

    /**
     * 播放器
     */
    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play(direction);
        }
    };


    /**
     * 执行播放
     *
     *
     */
    private synchronized void play(Direction direction) {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();

            switch (direction) {
                case LEFT:
                    currentItem++;
                    if (currentItem > count)
                        currentItem = 0;
                    break;
                case RIGHT:
                    currentItem--;
                    if (currentItem < 0)
                        currentItem = count;
                    break;
            }
            setCurrentItem(currentItem);
        }
        start();
    }



    /**
     * 播放上一个
     */
    public void previous() {
        if (direction == Direction.RIGHT) {
            play(Direction.LEFT);
        } else if (direction == Direction.LEFT) {
            play(Direction.RIGHT);
        }
    }

    /**
     * 播放下一个
     */
    public void next() {
        play(direction);
    }



    public enum Direction {
        /**
         * 向左行动，播放的应该是右边的
         */
        LEFT,
        /**
         * 向右行动，播放的应该是左边的
         */
        RIGHT
    }



}

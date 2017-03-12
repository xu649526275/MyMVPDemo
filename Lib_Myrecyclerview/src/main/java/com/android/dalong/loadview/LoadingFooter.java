package com.android.dalong.loadview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.dalong.R;

/**
 * Created by cundong on 2015/10/9.
 * <p/>
 * ListView/GridView/RecyclerView 分页加载时使用到的FooterView
 */
public class LoadingFooter extends RelativeLayout {

    protected State mState = State.Normal;
    private View mLoadingView;
    private View mNetworkErrorView;
    private View mTheEndView;
//    private ImageView mLoadingProgress;
//    private AnimationDrawable mAnimationDrawable;

    public LoadingFooter(Context context) {
        super(context);
        init(context);
    }

    public LoadingFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        inflate(context, R.layout.sample_common_list_footer, this);
        setOnClickListener(null);

        setState(State.Normal, true);
    }

    public State getState() {
        return mState;
    }

    public void setState(State status) {
        setState(status, true);
    }

    /**
     * 设置状态
     *
     * @param status
     * @param showView 是否展示当前View
     */
    public void setState(State status, boolean showView) {
        if (mState == status) {
            return;
        }
        mState = status;

        switch (status) {

            case Normal:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
//                    if (mAnimationDrawable != null) {
//                        mAnimationDrawable.stop();
//                    }
                }

                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                break;
            case Loading:
                setOnClickListener(null);
                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                if (mLoadingView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.loading_viewstub);
                    mLoadingView = viewStub.inflate();
//                    mLoadingProgress = (ImageView) mLoadingView.findViewById(R.id.loading_progress);

                } else {
                    mLoadingView.setVisibility(VISIBLE);
                }

                mLoadingView.setVisibility(showView ? VISIBLE : GONE);

//                mLoadingProgress.setVisibility(View.VISIBLE);
//                mAnimationDrawable = (AnimationDrawable) mLoadingProgress.getDrawable();
//                mAnimationDrawable.start();
                break;
            case TheEnd:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
//                    if (mAnimationDrawable != null) {
//                        mAnimationDrawable.stop();
//                    }
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                if (mTheEndView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.end_viewstub);
                    mTheEndView = viewStub.inflate();
                } else {
                    mTheEndView.setVisibility(VISIBLE);
                }

                mTheEndView.setVisibility(showView ? VISIBLE : GONE);
                break;
            case NetWorkError:

                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
//                    if (mAnimationDrawable != null) {
//                        mAnimationDrawable.stop();
//                    }
                }

                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.network_error_viewstub);
                    mNetworkErrorView = viewStub.inflate();
                } else {
                    mNetworkErrorView.setVisibility(VISIBLE);
                }

                mNetworkErrorView.setVisibility(showView ? VISIBLE : GONE);
                break;
            default:

                break;
        }
    }

    public static enum State {
        Normal/**正常*/
        , TheEnd/**加载到最底了*/
        , Loading/**加载中..*/
        , NetWorkError/**网络异常*/
    }
}
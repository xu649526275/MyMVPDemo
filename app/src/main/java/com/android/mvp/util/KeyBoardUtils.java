package com.android.mvp.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by 徐文龙 on 2015/11/16.
 */
public class KeyBoardUtils {
    /**
     * 关闭键盘
     *
     * @param context
     */
    public static void closeSoftInputKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                        .getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示键盘
     */
    public static void openSoftInputKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     * */
    public static void HideSoftInput(IBinder token, Context context) {

        if (token != null) {

            InputMethodManager manager = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            manager.hideSoftInputFromWindow(token,

                    InputMethodManager.HIDE_NOT_ALWAYS);

        }

    }
}

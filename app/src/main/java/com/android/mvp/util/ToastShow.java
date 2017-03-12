package com.android.mvp.util;

import android.content.Context;
import android.widget.Toast;

public class ToastShow {


    private Context context;
    public static Toast toast = null;

    public static ToastShow mToastShow;

    public ToastShow() {
    }

    public static ToastShow getInstance(Context context) {
        if (mToastShow == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
            mToastShow = new ToastShow();
        }
        return mToastShow;
    }

    public void toastShow(String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}
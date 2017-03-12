package com.android.mvp.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.android.mvp.R;


public class LoadingActivityDialog extends Dialog {

	private Context context = null;

	private static LoadingActivityDialog customProgressDialog = null;

	public LoadingActivityDialog(Context context) {

		super(context);

		this.context = context;

	}

	public LoadingActivityDialog(Context context, int theme) {

		super(context, theme);

	}

	public static LoadingActivityDialog createDialog(Context context) {

		customProgressDialog = new LoadingActivityDialog(context,
				R.style.CustomProgressDialog);

		customProgressDialog.setContentView(R.layout.dialog_loadding);

		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		customProgressDialog.setCanceledOnTouchOutside(false);
//
//		customProgressDialog.setCancelable(false);//返回键

		return customProgressDialog;

	}

}

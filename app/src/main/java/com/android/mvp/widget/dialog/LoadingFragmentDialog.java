package com.android.mvp.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.android.mvp.R;


public class LoadingFragmentDialog extends Dialog {

	private Context context = null;

	private static LoadingFragmentDialog customProgressDialog = null;

	public LoadingFragmentDialog(Context context) {

		super(context);

		this.context = context;

	}

	public LoadingFragmentDialog(Context context, int theme) {

		super(context, theme);

	}

	public static LoadingFragmentDialog createDialog(Context context) {

		customProgressDialog = new LoadingFragmentDialog(context,
				R.style.CustomProgressDialog);

		customProgressDialog.setContentView(R.layout.dialog_loadding_f);

		customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

		customProgressDialog.setCanceledOnTouchOutside(false);
//
//		customProgressDialog.setCancelable(false);//返回键

		return customProgressDialog;

	}

}

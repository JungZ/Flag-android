package com.flag.utils;

import com.flag.R;

import android.app.ProgressDialog;
import android.content.Context;

public class DialogUtils {
	public static ProgressDialog showWaitingDialog(Context context) {
		ProgressDialog progressDlg = new ProgressDialog(context);
		progressDlg.setMessage(ResourceUtils.getString(R.string.please_wait));
		progressDlg.setCancelable(true);
		progressDlg.show();
		return progressDlg;
	}
}

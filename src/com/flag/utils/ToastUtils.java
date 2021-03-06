package com.flag.utils;

import android.widget.Toast;

import com.flag.app.GlobalApplication;

public class ToastUtils {
	public static void show(int src) {
		show(ResourceUtils.getString(src));
	}

	public static void show(String msg) {
		Toast.makeText(GlobalApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}
}

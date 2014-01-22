package com.flag.utils;

import android.content.res.Resources;

import com.flag.app.GlobalApplication;

public class StringUtils {
	public static String getString(int resId) {
		Resources res = GlobalApplication.getInstance().getResources();
		return res.getString(resId);
	}
}

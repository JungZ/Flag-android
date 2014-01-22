package com.flag.utils;

import android.graphics.drawable.Drawable;

import com.flag.app.GlobalApplication;

public class ResourceUtils {
	public static String getString(int resId) {
		return GlobalApplication.getInstance().getResources().getString(resId);
	}
	
	public static Drawable getDrawable(int resId) {
		return GlobalApplication.getInstance().getResources().getDrawable(resId);
	}
}

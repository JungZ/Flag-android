package com.flag.app;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class Cache {
	public static final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
	public static final int cacheSize = maxMemory / 32;
	public static LruCache<String, Bitmap> bitmapCache = new LruCache<String, Bitmap>(cacheSize) {

		@Override
		protected int sizeOf(String key, Bitmap bitmap) {
			return bitmap.getByteCount() / 1024;
		}

	};
	public static LruCache<String, String> stringCache = new LruCache<String, String>(cacheSize) {

		@Override
		protected int sizeOf(String key, String value) {
			return value.getBytes().length / 1024;
		}

	};

	public static void addBitmapItem(String key, Bitmap bitmap) {
		if (getBitmapItem(key) == null)
			bitmapCache.put(key, bitmap);
	}

	public static Bitmap getBitmapItem(String key) {
		if (key != null && key.trim().length() != 0)
			return bitmapCache.get(key);
		else
			return null;
	}
	
	public static void addStringItem(String data) {
		if (!containsStringItem(data))
			stringCache.put(data, "");
	}

	public static boolean containsStringItem(String data) {
		if (data != null && data.trim().length() != 0)
			return stringCache.get(data) != null;
		else
			return false;
	}
}
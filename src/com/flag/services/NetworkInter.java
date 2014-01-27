package com.flag.services;

import java.io.IOException;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.flag.app.BitmapCache;
import com.flag.models.UserForm;
import com.flag.services.apis.FlagClient;
import com.flag.utils.BitmapDownloadTask;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

public class NetworkInter {
	static FlagClient client;

	static {
		FlagClient.Builder builder = new FlagClient.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
		builder.setApplicationName("Flag_Android");
		client = builder.build();
	}

	public static void getImage(View loader, ImageView imageView, String url) {
		Bitmap bitmap = BitmapCache.getBitmapItem(url);

		if (bitmap != null) {
			loader.setVisibility(View.GONE);
			imageView.setImageBitmap(bitmap);
		} else
			new BitmapDownloadTask(loader, imageView).execute(url);
	}

	public static <T> void insertUser(ResponseHandler<T> handler, final UserForm userForm) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.users().insert(userForm).execute();
			}

		}, handler);
	}

	public static <T> void getUser(ResponseHandler<T> handler, final UserForm userForm) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.users().get(userForm).execute();
			}

		}, handler);
	}

	public static <T> void flagList(ResponseHandler<T> handler, final double lat, final double lon) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.flags().list(lat, lon).execute();
			}

		}, handler);
	}

	public static <T> void getShop(ResponseHandler<T> handler, final long id) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.shops().get(id).execute();
			}

		}, handler);
	}

	public static <T> void itemList(ResponseHandler<T> handler, final long shopId) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.items().list(shopId).execute();
			}

		}, handler);
	}

}

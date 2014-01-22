package com.flag.services;

import java.io.IOException;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.flag.app.BitmapCache;
import com.flag.models.User;
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
	
	public static <T> void insertUser(ResponseHandler<T> handler, final User user) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.users().insert(user);
			}
			
		}, handler);
	}
	
	public static <T> void getUser(ResponseHandler<T> handler, final long id) {
		ThreadManager.execute(new Work<T>() {

			@SuppressWarnings("unchecked")
			@Override
			public T work() throws IOException {
				return (T) client.users().get(id).execute();
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
	
	public static void getImage(ImageView imageView, String url) {
		Bitmap bitmap = BitmapCache.getBitmapItem(url);
		
		if (bitmap != null)
			imageView.setImageBitmap(bitmap);
		else
			new BitmapDownloadTask(imageView).execute(url);
	}

}

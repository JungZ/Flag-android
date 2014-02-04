package com.flag.services;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.flag.app.Cache;
import com.flag.utils.BitmapUtils;

public class BitmapDownloadTask extends AsyncTask<String, Void, Bitmap> {
	private final WeakReference<View> loaderReference;
	private final WeakReference<ImageView> imageViewReference;

	public BitmapDownloadTask(View loader, ImageView imageView) {
		loaderReference = new WeakReference<View>(loader);
		imageViewReference = new WeakReference<ImageView>(imageView);
	}

	@Override
	protected Bitmap doInBackground(String... param) {
		return downloadBitmap(param[0]);
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		final View loader = loaderReference.get();
		final ImageView imageView = imageViewReference.get();
		if (loader != null && imageView != null && result != null) {
			loader.setVisibility(View.GONE);
			imageView.setImageBitmap(result);
		}
	}

	private Bitmap downloadBitmap(String url) {
		// initialize the default HTTP client object
		final DefaultHttpClient client = new DefaultHttpClient();
		// forming a HttoGet request
		final HttpGet getRequest = new HttpGet(url);

		try {
			HttpResponse response = client.execute(getRequest);

			// check 200 OK for success
			final int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w("ImageDownloader", "Error " + statusCode + " while retrieving bitmap from " + url);
				return null;
			}

			final HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = null;

				try {
					// getting contents from the stream
					inputStream = entity.getContent();

					// decoding stream data back into image Bitmap that android
					// understands
					final Bitmap bitmap = BitmapUtils.decodeStreamScaledDown(inputStream, BitmapUtils.longSide, BitmapUtils.shortSide);
					Cache.addBitmapItem(url, bitmap);

					return bitmap;
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
					entity.consumeContent();
				}
			}
		} catch (Exception e) {
			// You Could provide a more explicit error message for IOException
			getRequest.abort();
			Log.e("ImageDownloader", "Something went wrong while" + " retrieving bitmap from " + url + e.toString());
		}

		return null;
	}
}

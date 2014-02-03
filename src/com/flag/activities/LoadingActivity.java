package com.flag.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;

import com.flag.R;

@SuppressLint("HandlerLeak")
public class LoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);

		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				SharedPreferences prefs = getSharedPreferences("MyUser", MODE_PRIVATE);
				long id = prefs.getLong("id", 0);
				if (id == 0) {
					startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
					finish();
				} else {
					startActivity(new Intent(LoadingActivity.this, MapActivity.class));
					finish();
				}
			}
		};

		handler.sendEmptyMessageDelayed(0, 1000);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.loading, menu);
		return true;
	}

}

package com.flag.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.flag.R;
import com.flag.app.LocalUser;
import com.flag.models.RetainForm;
import com.flag.models.User;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;

@SuppressLint("HandlerLeak")
public class LoadingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);

		Handler handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if (LocalUser.getUser() != null) {
					goToMap();
					return;
				}

				long id = getSavedId();
				if (id == 0)
					goToLogin();
				else
					retainUser(id);
			}

		};

		handler.sendEmptyMessageDelayed(0, 1000);
	}

	private long getSavedId() {
		SharedPreferences prefs = getSharedPreferences("MyUser", MODE_PRIVATE);
		return prefs.getLong("id", 0);
	}

	private void retainUser(long id) {
		NetworkInter.retainUser(new ResponseHandler<User>() {

			@Override
			protected void onResponse(User response) {
				if (response == null) {
					goToLogin();
					return;
				}

				LocalUser.setUser(response);
				goToMap();
			}

		}, new RetainForm(id));
	}

	private void goToLogin() {
		startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
		finish();
	}

	private void goToMap() {
		startActivity(new Intent(LoadingActivity.this, MapActivity.class));
		finish();
	}
}

package com.flag.app;

import android.app.Application;

public class GlobalApplication extends Application {
	private static GlobalApplication instance = null;
	
	@Override
	public void onCreate() {
		initGlobalApplication();
	}

	@Override
	public void onTerminate() {
		instance = null;
		super.onTerminate();
	}

	public void initGlobalApplication() {
		instance = this;
	}

	public final static GlobalApplication getInstance() {
		return instance;
	}
}

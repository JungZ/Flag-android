package com.flag.app;

import android.app.Application;

import com.flag.utils.IBeaconUtils;
import com.radiusnetworks.ibeacon.Region;
import com.radiusnetworks.proximity.ibeacon.powersave.BackgroundPowerSaver;
import com.radiusnetworks.proximity.ibeacon.startup.BootstrapNotifier;

public class GlobalApplication extends Application implements BootstrapNotifier {
	private static GlobalApplication instance = null;
	@SuppressWarnings("unused")
	private BackgroundPowerSaver backgroundPowerSaver;
	private IBeaconUtils iBeaconUtils;

	@Override
	public void onCreate() {
		super.onCreate();
		initGlobalApplication();
		backgroundPowerSaver = new BackgroundPowerSaver(this);
		iBeaconUtils = new IBeaconUtils(this);
	}

	public void initGlobalApplication() {
		instance = this;
	}


	@Override
	public void onTerminate() {
		instance = null;
		super.onTerminate();
	}

	public final static GlobalApplication getInstance() {
		return instance;
	}

	public IBeaconUtils getIBeaconUtils() {
		return iBeaconUtils;
	}

	
	// below for BootstrapNotifier
	
	@Override
	public void didEnterRegion(Region region) {
		// TODO Auto-generated method stub

	}

	@Override
	public void didExitRegion(Region region) {
		// TODO Auto-generated method stub

	}

	@Override
	public void didDetermineStateForRegion(int i, Region region) {
		// TODO Auto-generated method stub

	}
}

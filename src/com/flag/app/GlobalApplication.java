package com.flag.app;

import android.app.Application;

import com.flag.utils.IBeaconUtils;
import com.flag.utils.RewardUtils;
import com.radiusnetworks.ibeacon.Region;
import com.radiusnetworks.proximity.ibeacon.powersave.BackgroundPowerSaver;
import com.radiusnetworks.proximity.ibeacon.startup.BootstrapNotifier;

public class GlobalApplication extends Application implements BootstrapNotifier {
	private static GlobalApplication instance;
	
	@SuppressWarnings("unused")
	private BackgroundPowerSaver backgroundPowerSaver;
	private RewardUtils rewardUtils;
	private IBeaconUtils iBeaconUtils;

	@Override
	public void onCreate() {
		super.onCreate();
		initGlobalApplication();
		backgroundPowerSaver = new BackgroundPowerSaver(this);
		rewardUtils = new RewardUtils(this);
		iBeaconUtils = new IBeaconUtils(this, rewardUtils);
	}

	public void initGlobalApplication() {
		instance = this;
		try {
			Class.forName("com.flag.services.NetworkInter");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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

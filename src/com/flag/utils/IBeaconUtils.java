package com.flag.utils;

import java.util.Collection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;

import com.radiusnetworks.ibeacon.IBeacon;
import com.radiusnetworks.ibeacon.IBeaconConsumer;
import com.radiusnetworks.ibeacon.IBeaconManager;
import com.radiusnetworks.ibeacon.RangeNotifier;
import com.radiusnetworks.ibeacon.Region;

public class IBeaconUtils implements IBeaconConsumer, RangeNotifier {
	private static final double AT_DISTANCE = 0.5;
	
	private Context context;
	private IBeaconManager iBeaconManager;

	public IBeaconUtils(Context context) {
		this.context = context;
		verifyBluetooth(context);
		iBeaconManager = IBeaconManager.getInstanceForApplication(context);
		iBeaconManager.bind(this);
	}

	public static void verifyBluetooth(final Context context) {
		try {
			if (!IBeaconManager.getInstanceForApplication(context).checkAvailability()) {
				final AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Bluetooth not enabled");
				builder.setMessage("Please enable bluetooth in settings and restart this application.");
				builder.setPositiveButton(android.R.string.ok, null);
				builder.show();
			}
		} catch (RuntimeException e) {
			final AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("Bluetooth LE not available");
			builder.setMessage("Sorry, this device does not support Bluetooth LE.");
			builder.setPositiveButton(android.R.string.ok, null);
			builder.show();
		}
	}

	@Override
	public Context getApplicationContext() {
		return context;
	}

	@Override
	public boolean bindService(Intent service, ServiceConnection conn, int flags) {
		return context.bindService(service, conn, flags);
	}

	@Override
	public void unbindService(ServiceConnection conn) {
		context.unbindService(conn);
	}

	@Override
	public void onIBeaconServiceConnect() {
		Region region = new Region("ApplicationRanging", null, null, null);
		try {
			iBeaconManager.startRangingBeaconsInRegion(region);
			iBeaconManager.setRangeNotifier(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void didRangeBeaconsInRegion(Collection<IBeacon> iBeacons, Region region) {
		for (IBeacon iBeacon : iBeacons)
			if (iBeacon.getAccuracy() < AT_DISTANCE)
				RewardUtils.checkIn(iBeacon.getProximityUuid());
	}
}

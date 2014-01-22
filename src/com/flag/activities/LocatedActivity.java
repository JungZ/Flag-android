package com.flag.activities;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;

public class LocatedActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {
	protected LocationClient locationClient;
	protected boolean isConnected = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		locationClient = new LocationClient(this, this, this);
		locationClient.connect();
	}

	@Override
	protected void onDestroy() {
		locationClient.disconnect();
		super.onDestroy();
	}

	@Override
	public void onConnected(Bundle data) {
		isConnected = true;
	}
	
	protected Location getLastLocation() {
		if (isConnected)
			return locationClient.getLastLocation();
		else
			return null;
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
	}

	@Override
	public void onDisconnected() {
	}
}

package com.flag.activities;

import java.util.List;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;

import com.flag.R;
import com.flag.models.FlagCollection;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements OnCameraChangeListener, ConnectionCallbacks, OnConnectionFailedListener {
	private LocationClient locationClient;
	private GoogleMap map;
	private FlagCollection flagCol;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		locationClient = new LocationClient(this, this, this);
		setUpMap();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		locationClient.connect();
	}
	
	@Override
	protected void onStop() {
		locationClient.disconnect();
		super.onStop();
	}

	private void setUpMap() {
		if (map == null)
			map = getMap();

		map.setOnCameraChangeListener(this);
		map.setMyLocationEnabled(true);
	}

	private GoogleMap getMap() {
		return ((MapFragment) getFragmentManager().findFragmentById(R.id.fragment_map_map)).getMap();
	}

	@Override
	public void onConnected(Bundle data) {
		getCurrentLocation();
	}

	private void getCurrentLocation() {
		Location location = locationClient.getLastLocation();
		setCenter(location.getLatitude(), location.getLongitude());
	}

	private void setCenter(double lat, double lon) {
		map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lon)));
	}

	@Override
	public void onCameraChange(CameraPosition camera) {
		getFlags();
	}

	private void getFlags() {
		if (zoomFar())
			return;
		
		NetworkInter.flagList(new ResponseHandler<FlagCollection>() {

			@Override
			protected void onResponse(FlagCollection response) {
				if (response == null || response.getFlags() == null)
					return;

				flagCol = response;
				showFlags();
			}

		}, map.getCameraPosition().target.latitude, map.getCameraPosition().target.longitude);
	}

	private boolean zoomFar() {
		return map.getCameraPosition().zoom < 14;
	}

	private void showFlags() {
		List<MarkerOptions> markerOptionsList = flagCol.markerOptionsList();
		for (MarkerOptions markerOptions : markerOptionsList)
			map.addMarker(markerOptions);
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
	}

	@Override
	public void onDisconnected() {
	}

}

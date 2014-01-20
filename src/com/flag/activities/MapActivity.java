package com.flag.activities;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.flag.R;
import com.flag.models.Flag;
import com.flag.models.FlagCollection;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;
import com.flag.utils.LocationUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MapActivity extends Activity implements OnCameraChangeListener, ConnectionCallbacks, OnConnectionFailedListener, OnInfoWindowClickListener {
	private LocationClient locationClient;
	private GoogleMap map;
	private Map<Marker, Long> markerMap = new HashMap<Marker, Long>();
	private LatLng prePosition;

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
		map.setOnInfoWindowClickListener(this);
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
		if (location == null)
			return;
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

		if (positionNear())
			return;

		NetworkInter.flagList(new ResponseHandler<FlagCollection>() {

			@Override
			protected void onResponse(FlagCollection response) {
				if (response == null || response.getFlags() == null)
					return;

				removeFlags();
				showFlags(response);
				savePosition();
			}

		}, map.getCameraPosition().target.latitude, map.getCameraPosition().target.longitude);
	}

	private boolean zoomFar() {
		return map.getCameraPosition().zoom < 14;
	}

	private boolean positionNear() {
		if (prePosition == null)
			return false;

		if (LocationUtils.isNearby(prePosition.latitude, prePosition.longitude, map.getCameraPosition().target.latitude, map.getCameraPosition().target.longitude))
			return true;
		else
			return false;
	}
	
	private void removeFlags() {
		markerMap.clear();
		map.clear();
	}

	private void showFlags(FlagCollection flagCol) {
		for (Flag flag : flagCol.getFlags())
			markerMap.put(map.addMarker(flag.toMarkerOptions()), flag.getShopId());
	}

	private void savePosition() {
		prePosition = map.getCameraPosition().target;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		long shopId = markerMap.get(marker);
		
		Intent intent = new Intent(this, ShopActivity.class);
		intent.putExtra("shopId", shopId);
		startActivity(intent);
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
	}

	@Override
	public void onDisconnected() {
	}
}

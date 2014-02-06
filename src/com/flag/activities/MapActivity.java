package com.flag.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flag.R;
import com.flag.app.LocalUser;
import com.flag.models.Flag;
import com.flag.models.FlagCollection;
import com.flag.models.FlagParcelable;
import com.flag.models.Shop;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;
import com.flag.utils.IBeaconUtils;
import com.flag.utils.LocationUtils;
import com.flag.utils.ResourceUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MapActivity extends LocatedActivity implements OnCameraChangeListener, OnMarkerClickListener, OnMapClickListener {
	public static final int FLAG_LIST_REQUEST = 0;

	private GoogleMap map;
	private Map<Marker, Flag> markerMap = new HashMap<Marker, Flag>();
	private LatLng prePosition;
	private Flag targetFlag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		setUpMap();
		IBeaconUtils.verifyBluetooth(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setUpInfo();
	}
	
	@Override
	public void onBackPressed() {
		if (targetFlag != null)
			hideDetails();
		else
			super.onBackPressed();
	}

	private void setUpMap() {
		if (map == null)
			map = getMap();

		map.setOnCameraChangeListener(this);
		map.setMyLocationEnabled(true);
		map.setOnMarkerClickListener(this);
		map.setOnMapClickListener(this);
	}

	private GoogleMap getMap() {
		return ((MapFragment) getFragmentManager().findFragmentById(R.id.fragment_map_map)).getMap();
	}

	private void setUpInfo() {
		Button buttonRewards = (Button) findViewById(R.id.button_map_rewards);
		buttonRewards.setText("" + LocalUser.getUser().getReward());
	}

	@Override
	public void onConnected(Bundle data) {
		super.onConnected(data);
		initializeLocation();
	}

	private void initializeLocation() {
		Location location = getLastLocation();
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

		}, LocalUser.getUser().getId(), map.getCameraPosition().target.latitude, map.getCameraPosition().target.longitude);
	}

	private boolean zoomFar() {
		return map.getCameraPosition().zoom < 14;
	}

	private boolean positionNear() {
		if (prePosition == null)
			return false;

		if (LocationUtils
				.isClose(prePosition.latitude, prePosition.longitude, map.getCameraPosition().target.latitude, map.getCameraPosition().target.longitude))
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
			markerMap.put(map.addMarker(flag.toMarkerOptions()), flag);
	}

	private void savePosition() {
		prePosition = map.getCameraPosition().target;
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 16));
		showDetails(marker);
		return true;
	}

	private void showDetails(Marker marker) {
		Flag flag = markerMap.get(marker);

		View infoView = findViewById(R.id.linear_map_flaginfo);
		infoView.setVisibility(View.VISIBLE);

		TextView textName = (TextView) findViewById(R.id.text_map_flag_name);
		textName.setText(flag.getShopName());

		TextView textReward = (TextView) findViewById(R.id.text_map_flag_reward);
		textReward.setText(ResourceUtils.getString(R.string.reward_info_check_in) + " " + flag.getReward());
		if (flag.isRewarded())
			textReward.append(" V");

		TextView textDesc = (TextView) findViewById(R.id.text_map_flag_description);
		textDesc.setText(flag.getShopDescription());

		targetFlag = flag;
	}

	@Override
	public void onMapClick(LatLng latLng) {
		hideDetails();
	}

	private void hideDetails() {
		View infoView = findViewById(R.id.linear_map_flaginfo);
		infoView.setVisibility(View.GONE);
		targetFlag = null;
	}

	public void goToFlagList(View view) {
		Intent intent = new Intent(this, FlagListActivity.class);
		intent.putParcelableArrayListExtra(FlagParcelable.EXTRA_FLAGPARCELABLE_LIST, getFlagParcelables());
		startActivityForResult(intent, FLAG_LIST_REQUEST);
	}

	private ArrayList<FlagParcelable> getFlagParcelables() {
		ArrayList<FlagParcelable> flagParcelables = new ArrayList<FlagParcelable>();
		for (Flag flag : markerMap.values())
			flagParcelables.add(flag.toParcelable());
		return flagParcelables;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == FLAG_LIST_REQUEST) {
			FlagParcelable flagParcelable = data.getParcelableExtra(FlagParcelable.EXTRA_FLAGPARCELABLE);
			focusFlag(flagParcelable.toFlag());
		}
	}

	private void focusFlag(Flag flag) {
		for (Entry<Marker, Flag> entry : markerMap.entrySet())
			if (entry.getValue().equals(flag))
				onMarkerClick(entry.getKey());
	}

	public void goToItems(View view) {
		Intent intent = new Intent(this, ItemsActivity.class);
		intent.putExtra(Shop.EXTRA_SHOP_ID, targetFlag.getShopId());
		intent.putExtra(Shop.EXTRA_SHOP_NAME, targetFlag.getShopName());
		startActivity(intent);
	}

	public void goToRewards(View view) {
		Intent intent = new Intent(this, RewardsActivity.class);
		startActivity(intent);
	}
}

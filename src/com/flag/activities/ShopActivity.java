package com.flag.activities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flag.R;
import com.flag.models.Flag;
import com.flag.models.Shop;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;
import com.flag.utils.LocationUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.model.LatLng;

public class ShopActivity extends SubActionActivity implements ConnectionCallbacks, OnConnectionFailedListener, SensorEventListener {
	private LocationClient locationClient;
	private SensorManager sensorManager;
	private long shopId;
	private LatLng target;
	private int shopSign;
	private boolean isConnected = false;
	private boolean shopSignFlag = false;
	private TextView textMagnetic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);

		if (savedInstanceState == null) {
			shopId = getIntent().getLongExtra(Shop.EXTRA_SHOP_ID, 0);
			target = getIntent().getParcelableExtra(Flag.EXTRA_LATLNG);
		}

		locationClient = new LocationClient(this, this, this);
		locationClient.connect();

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);

		textMagnetic = (TextView) findViewById(R.id.text_shop_magnetic);
	}

	@Override
	protected void onDestroy() {
		locationClient.disconnect();
		super.onDestroy();
	}

	@Override
	public void onResume() {
		super.onResume();
		getShop();
	}

	private void getShop() {
		NetworkInter.getShop(new ResponseHandler<Shop>() {

			@Override
			protected void onResponse(Shop response) {
				if (response == null)
					return;

				showShop(response);
				shopSign = response.getSign();
			}

		}, shopId);
	}

	private void showShop(Shop shop) {
		getActionBar().setTitle(shop.getName());

		ImageView imageProfile = (ImageView) findViewById(R.id.image_shop_profile);
		NetworkInter.getImage(imageProfile, shop.getImageUrl());

		TextView textDescription = (TextView) findViewById(R.id.text_shop_description);
		textDescription.setText(shop.getDescription());

		Button buttonCheckIn = (Button) findViewById(R.id.button_shop_check_in);
		buttonCheckIn.setText("Check In " + shop.getReward1());
	}

	public void checkIn(View view) {
		if (!isConnected)
			return;
		
		shopSignFlag = false;
		new CheckInTask().execute();
	}

	public class CheckInTask extends AsyncTask<Void, Void, Void> {
		private boolean flag = false;

		@Override
		protected Void doInBackground(Void... params) {
			Location location = locationClient.getLastLocation();

			synchronized (this) {
				for (int i = 0; i < 50; i++) {
					try {
						if (LocationUtils.isClose(location.getLatitude(), location.getLongitude(), target.latitude, target.longitude) && shopSignFlag)
							flag = true;
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Button buttonCheckIn = (Button) findViewById(R.id.button_shop_check_in);
			if (flag)
				buttonCheckIn.setText("(Checked In!)");
			else
				buttonCheckIn.setText("(Check In Failed)");
		}

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized (this) {
			if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				textMagnetic.setText(String.valueOf(LocationUtils.magnitude(event.values[0], event.values[1], event.values[2])));
				if (LocationUtils.closeValue(LocationUtils.magnitude(event.values[0], event.values[1], event.values[2]), shopSign))
					shopSignFlag = true;
			}
		}
	}

	public void goToItems(View view) {
		// TODO
	}

	@Override
	public void onConnected(Bundle data) {
		isConnected = true;
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
	}

	@Override
	public void onDisconnected() {
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

}

package com.flag.activities;

import android.content.Intent;
import android.location.Location;
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
import com.flag.utils.ResourceUtils;
import com.flag.utils.ToastUtils;
import com.google.android.gms.maps.model.LatLng;

public class ShopActivity extends LocatedSubCategoryActivity {
	private long shopId;
	private LatLng target;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);

		if (savedInstanceState == null) {
			shopId = getIntent().getLongExtra(Shop.EXTRA_SHOP_ID, 0);
			target = getIntent().getParcelableExtra(Flag.EXTRA_LATLNG);
		}
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
		buttonCheckIn.setText(ResourceUtils.getString(R.string.check_in) + " " + shop.getReward1());
	}

	public void checkIn(View view) {
		Location location = getLastLocation();
		if (location == null)
			return;

		if (LocationUtils.isClose(location.getLatitude(), location.getLongitude(), target.latitude, target.longitude))
			onCheckInSuccess();
		else
			onCheckInFail();
	}

	private void onCheckInSuccess() {
		ToastUtils.show("checked!");
	}

	private void onCheckInFail() {
		ToastUtils.show("fail...");
	}

	public void goToItems(View view) {
		Intent intent = new Intent(this, ItemsActivity.class);
		intent.putExtra(Shop.EXTRA_SHOP_ID, shopId);
		startActivity(intent);
	}

}

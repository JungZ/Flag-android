package com.flag.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flag.R;
import com.flag.app.LocalUser;
import com.flag.models.Shop;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;
import com.flag.utils.ResourceUtils;

public class ShopActivity extends LocatedSubCategoryActivity {
	private long shopId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);

		if (savedInstanceState == null)
			shopId = getIntent().getLongExtra(Shop.EXTRA_SHOP_ID, 0);
	}

	@Override
	protected void onResume() {
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

		}, LocalUser.getUser().getId(), shopId);
	}

	private void showShop(Shop shop) {
		getActionBar().setTitle(shop.getName());

		ImageView imageProfile = (ImageView) findViewById(R.id.image_shop_profile);
		View loader = findViewById(R.id.progressbar_shop_profile);
		NetworkInter.getImage(loader, imageProfile, shop.getImageUrl());

		TextView textDescription = (TextView) findViewById(R.id.text_shop_description);
		textDescription.setText(shop.getDescription());

		TextView textCheckIn = (TextView) findViewById(R.id.text_shop_check_in);
		textCheckIn.setText(ResourceUtils.getString(R.string.check_in) + " " + shop.getReward());
	}

	public void goToItems(View view) {
		Intent intent = new Intent(this, ItemsActivity.class);
		intent.putExtra(Shop.EXTRA_SHOP_ID, shopId);
		startActivity(intent);
	}

}

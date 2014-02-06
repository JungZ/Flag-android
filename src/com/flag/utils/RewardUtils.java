package com.flag.utils;

import android.content.Context;

import com.flag.app.Cache;
import com.flag.app.LocalUser;
import com.flag.models.Reward;
import com.flag.models.Shop;
import com.flag.models.User;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;

public class RewardUtils {
	private Context context;
	
	public RewardUtils(Context context) {
		super();
		this.context = context;
	}

	public void checkIn(String beaconId) {
		if (isCachedItem(beaconId))
			return;
		
		getShopWithBeacon(beaconId);
		Cache.addStringItem(beaconId);
	}

	public void itemScan(String barcodeId) {
		if (isCachedItem(barcodeId))
			return;

		getItemWithBarcode(barcodeId);
		Cache.addStringItem(barcodeId);
	}

	private boolean isCachedItem(String beaconId) {
		return Cache.containsStringItem(beaconId);
	}

	private void getShopWithBeacon(String beaconId) {
		NetworkInter.getShopWithBeacon(new ResponseHandler<Shop>(context.getMainLooper()) {

			@Override
			protected void onResponse(Shop response) {
				if (response == null)
					return;

				if (!response.isRewarded())
					claimReward(new Reward(LocalUser.getUser().getId(), response.getId(), Reward.TYPE_SHOP, response.getReward()), response.getName());
				else
					NotificationUtils.notifyShop(response);
			}

		}, LocalUser.getUser().getId(), beaconId);
	}

	private void getItemWithBarcode(String barcodeId) {
		// TODO Auto-generated method stub

	}

	private void claimReward(final Reward reward, final String targetName) {
		NetworkInter.claimReward(new ResponseHandler<User>(context.getMainLooper()) {

			@Override
			protected void onResponse(User response) {
				if (response != null) {
					LocalUser.setUser(response);
					NotificationUtils.notifyReward(reward, targetName);
				}
			}

		}, reward);
	}
}

package com.flag.utils;

import com.flag.app.Cache;
import com.flag.app.LocalUser;
import com.flag.models.Reward;
import com.flag.models.Shop;
import com.flag.models.User;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;

public class RewardUtils {
	public static void checkIn(String beaconId) {
		if (isCachedItem(beaconId))
			return;

		getShopWithBeacon(beaconId);
		Cache.addStringItem(beaconId);
	}

	public static void itemScan(String barcodeId) {
		if (isCachedItem(barcodeId))
			return;
		
		getItemWithBarcode(barcodeId);
		Cache.addStringItem(barcodeId);
	}

	private static boolean isCachedItem(String beaconId) {
		return Cache.containsStringItem(beaconId);
	}

	private static void getShopWithBeacon(String beaconId) {
		NetworkInter.getShopWithBeacon(new ResponseHandler<Shop>() {

			@Override
			protected void onResponse(Shop response) {
				NotificationUtils.notifyShop(response);
				
				if (!response.isRewarded())
					claimReward(new Reward(LocalUser.getUser().getId(), response.getId(), Reward.TYPE_SHOP, response.getReward()));
			}
			
		}, LocalUser.getUser().getId(), beaconId);
	}

	private static void getItemWithBarcode(String barcodeId) {
		// TODO Auto-generated method stub
		
	}
	
	private static void claimReward(final Reward reward) {
		NetworkInter.claimReward(new ResponseHandler<User>() {

			@Override
			protected void onResponse(User response) {
				if (response != null) {
					LocalUser.setUser(response);
					NotificationUtils.notifyReward(reward);
				}
			}
			
		}, reward);
	}
}

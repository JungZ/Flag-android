package com.flag.utils;

import com.flag.app.Cache;

public class CheckInUtils {
	public static void checkIn(String beaconId) {
		if (isCachedItem(beaconId))
			return;

		getShopWithBeaconId(beaconId);
		Cache.addStringItem(beaconId);
	}

	private static boolean isCachedItem(String beaconId) {
		return Cache.containsStringItem(beaconId);
	}

	private static void getShopWithBeaconId(String beaconId) {
		// TODO Auto-generated method stub

	}
}

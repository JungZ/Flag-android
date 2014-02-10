package com.flag.utils;

import android.content.Context;

import com.flag.activities.MainActivity;
import com.flag.models.Reward;
import com.flag.models.Shop;

public class NotificationUtils {

	public static void notifyShop(Shop shop) {
		Context context = MainActivity.getContext();
		
		if (context != null)
			DialogUtils.notifyShop(context, shop);
		else
			ToastUtils.show("you are near " + shop.getName() + "!");
	}

	public static void notifyReward(Reward reward, String targetName) {
		ToastUtils.show(targetName + " rewarded with " + reward.getReward() + "!");
	}

}
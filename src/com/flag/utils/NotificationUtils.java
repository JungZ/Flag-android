package com.flag.utils;

import com.flag.models.Reward;
import com.flag.models.Shop;

public class NotificationUtils {

	public static void notifyShop(Shop response) {
		// TODO Auto-generated method stub
		
	}

	public static void notifyReward(Reward reward, String targetName) {
		ToastUtils.show(targetName + " rewarded with " + reward.getReward() + "!");
	}

}

package com.flag.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

import com.flag.R;
import com.flag.activities.ItemsActivity;
import com.flag.models.Shop;

public class DialogUtils {
	public static ProgressDialog getWaitingDialog(Context context) {
		ProgressDialog progressDlg = new ProgressDialog(context);
		progressDlg.setMessage(ResourceUtils.getString(R.string.please_wait));
		progressDlg.setCancelable(true);
		progressDlg.show();
		return progressDlg;
	}

	public static void notifyShop(final Context context, final Shop shop) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage("you are near " + shop.getName() + "!\nwant to check on it?");
		builder.setPositiveButton(R.string.ok, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(context, ItemsActivity.class);
				intent.putExtra(Shop.EXTRA_SHOP_ID, shop.getId());
				intent.putExtra(Shop.EXTRA_SHOP_NAME, shop.getName());
				context.startActivity(intent);
			}
			
		});
		builder.setNegativeButton(R.string.no, null);
		builder.show();
	}
}

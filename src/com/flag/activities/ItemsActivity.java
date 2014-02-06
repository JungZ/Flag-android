package com.flag.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.flag.R;
import com.flag.adapters.ItemAdapter;
import com.flag.app.LocalUser;
import com.flag.models.Item;
import com.flag.models.ItemCollection;
import com.flag.models.Shop;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;
import com.flag.utils.ToastUtils;
import com.google.zxing.client.android.CaptureActivity;

public class ItemsActivity extends LocatedSubCategoryActivity implements ItemAdapter.ItemScanInter {
	public static final int ITEM_SCAN_REQUEST = 0;

	private long shopId;
	private String shopName;
	private GridView gridItems;
	private List<Item> items;
	private ItemAdapter itemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);

		if (savedInstanceState == null) {
			shopId = getIntent().getLongExtra(Shop.EXTRA_SHOP_ID, 0);
			shopName = getIntent().getStringExtra(Shop.EXTRA_SHOP_NAME);
		}
		
		getActionBar().setTitle(shopName);

		gridItems = (GridView) findViewById(R.id.grid_items_items);
		items = new ArrayList<Item>();
		itemAdapter = new ItemAdapter(this, items);
		gridItems.setAdapter(itemAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		getItems();
	}

	private void getItems() {
		NetworkInter.itemList(new ResponseHandler<ItemCollection>() {

			@Override
			protected void onResponse(ItemCollection response) {
				if (response == null || response.getItems() == null)
					return;

				items.clear();
				items.addAll(response.getItems());
				refresh();
			}

		}, LocalUser.getUser().getId(), shopId);
	}

	private void refresh() {
		itemAdapter.notifyDataSetChanged();
	}

	@Override
	public void scanItem() {
		Intent intent = new Intent(this, CaptureActivity.class);
		intent.setAction("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "BAR_CODE_MODE");
		startActivityForResult(intent, ITEM_SCAN_REQUEST);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (resultCode == RESULT_OK && requestCode == ITEM_SCAN_REQUEST) {
			String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
			String contents = intent.getStringExtra("SCAN_RESULT");
			ToastUtils.show(format + " / " + contents);
		}
	}
}

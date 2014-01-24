package com.flag.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.flag.R;
import com.flag.adapters.ItemAdapter;
import com.flag.models.Item;
import com.flag.models.ItemCollection;
import com.flag.models.Shop;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;
import com.flag.utils.ToastUtils;

public class ItemsActivity extends LocatedSubCategoryActivity implements ItemAdapter.ItemScanInter {
	private long shopId;
	private GridView gridItems;
	private List<Item> items;
	private ItemAdapter itemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);

		if (savedInstanceState == null)
			shopId = getIntent().getLongExtra(Shop.EXTRA_SHOP_ID, 0);

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

		}, shopId);
	}

	private void refresh() {
		itemAdapter.notifyDataSetChanged();
	}

	@Override
	public void scanItem() {
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {

			if (resultCode == RESULT_OK) {
				String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
				String contents = intent.getStringExtra("SCAN_RESULT");
				ToastUtils.show(format + " / " + contents);
			} else if (resultCode == RESULT_CANCELED) {
			}
		}
	}
}

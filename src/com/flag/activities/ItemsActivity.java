package com.flag.activities;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.widget.GridView;

import com.flag.R;
import com.flag.adapters.ItemAdapter;
import com.flag.models.Item;
import com.flag.models.ItemCollection;
import com.flag.models.Shop;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;

public class ItemsActivity extends LocatedSubCategoryActivity {
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

}

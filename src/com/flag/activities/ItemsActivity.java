package com.flag.activities;

import android.os.Bundle;
import android.widget.GridView;

import com.flag.R;
import com.flag.models.Shop;

public class ItemsActivity extends LocatedSubCategoryActivity {
	private long shopId;
	private GridView gridItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_items);

		if (savedInstanceState == null)
			shopId = getIntent().getLongExtra(Shop.EXTRA_SHOP_ID, 0);

		gridItems = (GridView) findViewById(R.id.grid_items_items);
	}

}

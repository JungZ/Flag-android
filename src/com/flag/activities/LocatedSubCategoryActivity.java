package com.flag.activities;

import android.os.Bundle;
import android.view.MenuItem;

public class LocatedSubCategoryActivity extends LocatedActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		switch(item.getItemId()) {
		case android.R.id.home:
			super.onBackPressed();
			break;
		}
		
		return true;
	}
}

package com.flag.activities;

import android.os.Bundle;
import android.view.Menu;

import com.flag.R;

public class RewardsActivity extends SubCategoryActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.reward, menu);
		return true;
	}

}

package com.flag.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.flag.R;

public class RewardsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reward, menu);
		return true;
	}

}
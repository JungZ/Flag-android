package com.flag.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.flag.R;
import com.flag.adapters.FlagAdapter;
import com.flag.models.Flag;
import com.flag.models.FlagParcelable;

public class FlagListActivity extends SubCategoryActivity implements OnItemClickListener {
	private ListView listFlags;
	private FlagAdapter flagAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flag_list);
		
		List<FlagParcelable> flagParcelables = getIntent().getParcelableArrayListExtra(FlagParcelable.EXTRA_FLAGPARCELABLE_LIST);
		List<Flag> flags = new ArrayList<Flag>();
		for (FlagParcelable flagParcelable : flagParcelables)
			flags.add(flagParcelable.toFlag());
		flagAdapter = new FlagAdapter(this, flags);

		listFlags = (ListView) findViewById(R.id.list_flag_list_flag_list);
		listFlags.setAdapter(flagAdapter);
		listFlags.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		Flag flag = (Flag) flagAdapter.getItem(position);
		Intent returnIntent = new Intent();
		returnIntent.putExtra(FlagParcelable.EXTRA_FLAGPARCELABLE, flag.toParcelable());
		setResult(RESULT_OK, returnIntent);
		finish();
	}

}

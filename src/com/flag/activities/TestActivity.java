package com.flag.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.flag.R;
import com.flag.models.FlagCollection;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandler;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
	}

	public void flagList(View view) {
		EditText editLat = (EditText) findViewById(R.id.edit_test_lat);
		EditText editLon = (EditText) findViewById(R.id.edit_test_lon);

		double lat = Double.valueOf(editLat.getEditableText().toString());
		double lon = Double.valueOf(editLon.getEditableText().toString());

		NetworkInter.flagList(new ResponseHandler<FlagCollection>() {
			
			@Override
			public void onResponse(FlagCollection flagCol) {
				if (flagCol == null)
					return;
				
				if (flagCol.getFlags() == null)
					return;

				TextView result = (TextView) findViewById(R.id.text_test_result);
				result.setText(flagCol.toString());
			}
			
		}, lat, lon);
	}
}

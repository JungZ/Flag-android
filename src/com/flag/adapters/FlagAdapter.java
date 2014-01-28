package com.flag.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.flag.R;
import com.flag.models.Flag;

public class FlagAdapter extends BaseAdapter {
	private Context context;
	private List<Flag> flags;

	public FlagAdapter(Context context, List<Flag> flags) {
		super();
		this.context = context;
		this.flags = flags;
	}

	@Override
	public int getCount() {
		return flags.size();
	}

	@Override
	public Object getItem(int position) {
		return flags.get(position);
	}

	@Override
	public long getItemId(int position) {
		return flags.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Flag flag = (Flag) getItem(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.adapted_flag_layout, parent, false);

		TextView textName = (TextView) view.findViewById(R.id.text_flag_name);
		textName.setText(flag.getShopName());

		TextView textReward = (TextView) view.findViewById(R.id.text_flag_reward);
		textReward.setText("" + flag.getReward1() + " ~ " + flag.getReward2());
		
		return view;
	}

}

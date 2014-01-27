package com.flag.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flag.R;
import com.flag.models.Item;
import com.flag.services.NetworkInter;
import com.flag.utils.ResourceUtils;

public class ItemAdapter extends BaseAdapter {
	private Context context;
	private ItemScanInter itemScanInter;
	private List<Item> items;

	public interface ItemScanInter {
		public abstract void scanItem();
	}

	public ItemAdapter(Context context, List<Item> items) {
		super();
		this.context = context;
		this.itemScanInter = (ItemScanInter) context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Item item = (Item) getItem(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.adapted_item_layout, parent, false);

		ImageView imageProfile = (ImageView) view.findViewById(R.id.image_item_profile);
		View loader = view.findViewById(R.id.progressbar_item_profile);
		NetworkInter.getImage(loader, imageProfile, item.getImageUrl());

		ImageView imageBadge = (ImageView) view.findViewById(R.id.image_item_badge);
		imageBadge.setImageDrawable(ResourceUtils.getDrawable(R.drawable.common_signin_btn_icon_dark));

		TextView textName = (TextView) view.findViewById(R.id.text_item_name);
		textName.setText(item.getName());

		TextView textDescription = (TextView) view.findViewById(R.id.text_item_description);
		textDescription.setText(item.getDescription());

		Button buttonLikeIt = (Button) view.findViewById(R.id.button_item_like_it);
		buttonLikeIt.setText(ResourceUtils.getString(R.string.like_it) + " " + item.getReward());
		buttonLikeIt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				itemScanInter.scanItem();
			}
			
		});

		return view;
	}
}

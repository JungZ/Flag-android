package com.flag.models;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class FlagCollection extends GenericJson {
	@Key
	private List<Flag> flags;

	public FlagCollection() {
		super();
	}

	public FlagCollection(List<Flag> flags) {
		super();
		this.flags = flags;
	}

	public List<Flag> getFlags() {
		return flags;
	}

	public void setFlags(List<Flag> flags) {
		this.flags = flags;
	}

	public List<MarkerOptions> toMarkerOptionsList() {
		List<MarkerOptions> markerOptionsList = new ArrayList<MarkerOptions>();
		
		for (Flag flag : flags) {
			MarkerOptions markerOptions = new MarkerOptions();
			markerOptions.position(new LatLng(flag.getLat(), flag.getLon()));
			markerOptions.title(String.valueOf(flag.getShopId()));
			markerOptions.draggable(false);
			markerOptionsList.add(markerOptions);
		}
		
		return markerOptionsList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Flag flag : flags)
			sb.append(" id:" + flag.getId());

		return sb.toString();
	}
}

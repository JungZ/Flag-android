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

	public List<MarkerOptions> markerOptionsList() {
		List<MarkerOptions> markers = new ArrayList<MarkerOptions>();
		
		for (Flag flag : flags) {
			MarkerOptions marker = new MarkerOptions();
			marker.position(new LatLng(flag.getLat(), flag.getLon()));
			marker.title(String.valueOf(flag.getShopId()));
			marker.draggable(false);
			markers.add(marker);
		}
		
		return markers;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Flag flag : flags)
			sb.append(" id:" + flag.getId());

		return sb.toString();
	}
}

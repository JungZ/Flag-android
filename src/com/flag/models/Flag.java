package com.flag.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class Flag extends GenericJson {
	public static final String EXTRA_LATLNG = "com.flag.models.extra.latlng";

	@Key
	@JsonString
	private Long id;

	@Key
	private double lat;

	@Key
	private double lon;

	@Key
	private long createdAt;

	@Key
	@JsonString
	private Long shopId;

	@Key
	private String shopName;

	@Key
	private int reward1;

	@Key
	private int reward2;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getReward1() {
		return reward1;
	}

	public void setReward1(int reward1) {
		this.reward1 = reward1;
	}

	public int getReward2() {
		return reward2;
	}

	public void setReward2(int reward2) {
		this.reward2 = reward2;
	}

	public MarkerOptions toMarkerOptions() {
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(new LatLng(lat, lon));
		markerOptions.title(shopName);
		markerOptions.draggable(false);

		return markerOptions;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("id=" + id).append(", lat=" + lat).append(", lon=" + lon).append(", createdAt=" + createdAt).append(", shopId=" + shopId);

		return sb.toString();
	}
}

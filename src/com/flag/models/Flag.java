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
	private String shopDescription;

	@Key
	private int reward;

	@Key
	private boolean rewarded;

	public Flag() {
		super();
	}

	public Flag(FlagParcelable flagParcelable) {
		super();
		id = flagParcelable.getId();
		lat = flagParcelable.getLat();
		lon = flagParcelable.getLon();
		createdAt = flagParcelable.getCreatedAt();
		shopId = flagParcelable.getShopId();
		shopName = flagParcelable.getShopName();
		shopDescription = flagParcelable.getShopDescription();
		reward = flagParcelable.getReward();
		rewarded = flagParcelable.isRewarded();
	}

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

	public String getShopDescription() {
		return shopDescription;
	}

	public void setShopDescription(String shopDescription) {
		this.shopDescription = shopDescription;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public boolean isRewarded() {
		return rewarded;
	}

	public void setRewarded(boolean rewarded) {
		this.rewarded = rewarded;
	}

	public MarkerOptions toMarkerOptions() {
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(new LatLng(lat, lon));
		markerOptions.title(shopName);
		markerOptions.draggable(false);

		return markerOptions;
	}

	public FlagParcelable toParcelable() {
		return new FlagParcelable(this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("id=" + id).append(", lat=" + lat).append(", lon=" + lon).append(", createdAt=" + createdAt).append(", shopId=" + shopId);

		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		try {
			Flag f = (Flag) o;
			if (id.equals(f.getId()))
				return true;
		} catch (Exception e) {
			return false;
		}

		return false;
	}
}

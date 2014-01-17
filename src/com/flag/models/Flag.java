package com.flag.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class Flag extends GenericJson {
	public static final double RADIUS = 0.0044762327;

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
	private ShopHint shopHint;

	public Long getId() {
		return id;
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

	public ShopHint getShopHint() {
		return shopHint;
	}

	public void setShopHint(ShopHint shopHint) {
		this.shopHint = shopHint;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("id=" + id).append(", lat=" + lat).append(", lon=" + lon).append(", createdAt=" + createdAt).append(", shopId=" + shopId);

		return sb.toString();
	}
}

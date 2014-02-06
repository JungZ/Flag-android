package com.flag.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

public class Shop extends GenericJson {
	public static final String EXTRA_SHOP_ID = "com.flag.models.extra.shop.id";
	public static final String EXTRA_SHOP_NAME = "com.flag.models.extra.shop.name";

	@Key
	@JsonString
	private Long id;

	@Key
	private String name;

	@Key
	private String imageUrl;

	@Key
	private int type;

	@Key
	private String description;

	@Key
	private int reward;

	@Key
	private boolean rewarded;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}

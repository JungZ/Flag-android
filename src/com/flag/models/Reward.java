package com.flag.models;

import java.util.Date;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class Reward extends GenericJson {
	public static final long TYPE_SHOP = 101;
	public static final long TYPE_ITEM = 202;

	@Key
	private Long userId;

	@Key
	private Long targetId;

	@Key
	private Long type;

	@Key
	private int reward;

	@Key
	private long createdAt;

	public Reward() {
		super();
	}

	public Reward(Long userId, Long targetId, Long type, int reward) {
		super();
		this.userId = userId;
		this.targetId = targetId;
		this.type = type;
		this.reward = reward;
		createdAt = new Date().getTime();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
}

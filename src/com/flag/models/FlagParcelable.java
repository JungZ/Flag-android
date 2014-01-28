package com.flag.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FlagParcelable implements Parcelable {
	public static final String EXTRA_FLAGPARCELABLE = "com.flag.models.extra.flagparcelable";
	public static final String EXTRA_FLAGPARCELABLE_LIST = "com.flag.models.extra.flagparcelable.list";
	
	private long id;
	private double lat;
	private double lon;
	private long createdAt;
	private long shopId;
	private String shopName;
	private int reward1;
	private int reward2;

	public FlagParcelable() {
		super();
	}

	public FlagParcelable(Flag flag) {
		id = flag.getId();
		lat = flag.getLat();
		lon = flag.getLon();
		createdAt = flag.getCreatedAt();
		shopId = flag.getShopId();
		shopName = flag.getShopName();
		reward1 = flag.getReward1();
		reward2 = flag.getReward2();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
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
	
	public Flag toFlag() {
		return new Flag(this);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<FlagParcelable> CREATOR = new Parcelable.Creator<FlagParcelable>() {

		@Override
		public FlagParcelable createFromParcel(Parcel source) {
			return new FlagParcelable(source);
		}

		@Override
		public FlagParcelable[] newArray(int size) {
			return new FlagParcelable[size];
		}
	};

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeDouble(lat);
		dest.writeDouble(lon);
		dest.writeLong(createdAt);
		dest.writeLong(shopId);
		dest.writeString(shopName);
		dest.writeInt(reward1);
		dest.writeInt(reward2);
	}

	public FlagParcelable(Parcel source) {
		id = Long.valueOf(source.readLong());
		lat = source.readDouble();
		lon = source.readDouble();
		createdAt = source.readLong();
		shopId = Long.valueOf(source.readLong());
		shopName = source.readString();
		reward1 = source.readInt();
		reward2 = source.readInt();
	}
}

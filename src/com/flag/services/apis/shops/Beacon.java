package com.flag.services.apis.shops;

import java.io.IOException;

import com.flag.models.Shop;
import com.flag.services.apis.FlagClient;
import com.flag.services.apis.FlagRequest;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;

public class Beacon extends FlagRequest<Shop> {
	private static final String REST_PATH = "beacon";

	protected Beacon(FlagClient client) {
		super(client, "GET", REST_PATH, null, Shop.class);
	}

	@Override
	public HttpResponse executeUsingHead() throws IOException {
		return super.executeUsingHead();
	}

	@Override
	public HttpRequest buildHttpRequestUsingHead() throws IOException {
		return super.buildHttpRequestUsingHead();
	}

	@Override
	public Beacon setAlt(String alt) {
		return (Beacon) super.setAlt(alt);
	}

	@Override
	public Beacon setFields(String fields) {
		return (Beacon) super.setFields(fields);
	}

	@Override
	public Beacon setKey(String key) {
		return (Beacon) super.setKey(key);
	}

	@Override
	public Beacon setOauthToken(String oauthToken) {
		return (Beacon) super.setOauthToken(oauthToken);
	}

	@Override
	public Beacon setPrettyPrint(Boolean prettyPrint) {
		return (Beacon) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Beacon setQuotaUser(String quotaUser) {
		return (Beacon) super.setQuotaUser(quotaUser);
	}

	@Override
	public Beacon setUserIp(String userIp) {
		return (Beacon) super.setUserIp(userIp);
	}

	@Key
	private Long userId;

	@Key
	private String beaconId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBeaconId() {
		return beaconId;
	}

	public void setBeaconId(String beaconId) {
		this.beaconId = beaconId;
	}
}

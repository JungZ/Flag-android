package com.flag.services.apis.flags;

import java.io.IOException;

import com.flag.models.FlagCollection;
import com.flag.services.apis.FlagClient;
import com.flag.services.apis.FlagRequest;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Key;

public class List extends FlagRequest<FlagCollection> {
	private static final String REST_PATH = "flag";

	protected List(FlagClient client) {
		super(client, "GET", REST_PATH, null, FlagCollection.class);
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
	public List setAlt(String alt) {
		return (List) super.setAlt(alt);
	}

	@Override
	public List setFields(String fields) {
		return (List) super.setFields(fields);
	}

	@Override
	public List setKey(String key) {
		return (List) super.setKey(key);
	}

	@Override
	public List setOauthToken(String oauthToken) {
		return (List) super.setOauthToken(oauthToken);
	}

	@Override
	public List setPrettyPrint(Boolean prettyPrint) {
		return (List) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public List setQuotaUser(String quotaUser) {
		return (List) super.setQuotaUser(quotaUser);
	}

	@Override
	public List setUserIp(String userIp) {
		return (List) super.setUserIp(userIp);
	}

	@Key
	private long userId;

	@Key
	private double lat;

	@Key
	private double lon;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

}

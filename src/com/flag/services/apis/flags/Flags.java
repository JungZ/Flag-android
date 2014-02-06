package com.flag.services.apis.flags;

import java.io.IOException;

import com.flag.services.apis.FlagClient;

public class Flags {
	private FlagClient client;

	public Flags(FlagClient client) {
		super();
		this.client = client;
	}

	public List list(long userId, double lat, double lon) throws IOException {
		List list = new List(client);
		list.setUserId(userId);
		list.setLat(lat);
		list.setLon(lon);
		client.initialize(list);
		return list;
	}
}

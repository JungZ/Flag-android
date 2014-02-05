package com.flag.services.apis.shops;

import java.io.IOException;

import com.flag.services.apis.FlagClient;

public class Shops {
	private FlagClient client;

	public Shops(FlagClient client) {
		super();
		this.client = client;
	}
	
	public Get get(Long userId, Long id) throws IOException {
		Get get = new Get(client);
		get.setUserId(userId);
		get.setId(id);
		client.initialize(get);
		return get;
	}
	
	public Beacon beacon(Long userId, String beaconId) throws IOException {
		Beacon beacon = new Beacon(client);
		beacon.setUserId(userId);
		beacon.setBeaconId(beaconId);
		client.initialize(beacon);
		return beacon;
	}
}

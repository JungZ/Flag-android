package com.flag.services.apis.shops;

import java.io.IOException;

import com.flag.services.apis.FlagClient;

public class Shops {
	private FlagClient client;

	public Shops(FlagClient client) {
		super();
		this.client = client;
	}
	
	public Get get(Long id) throws IOException {
		Get get = new Get(client);
		get.setId(id);
		client.initialize(get);
		return get;
	}
}

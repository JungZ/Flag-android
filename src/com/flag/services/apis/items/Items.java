package com.flag.services.apis.items;

import java.io.IOException;

import com.flag.services.apis.FlagClient;

public class Items {
	private FlagClient client;
	
	public Items(FlagClient client) {
		super();
		this.client = client;
	}
	
	public List list(Long shopId) throws IOException {
		List list = new List(client);
		list.setShopId(shopId);
		client.initialize(list);
		return list;
	}

}

package com.flag.services.apis.rewards;

import java.io.IOException;

import com.flag.models.Reward;
import com.flag.services.apis.FlagClient;

public class Rewards {
	private FlagClient client;

	public Rewards(FlagClient client) {
		super();
		this.client = client;
	}
	
	public Insert insert(Reward reward) throws IOException {
		Insert insert = new Insert(client, reward);
		client.initialize(insert);
		return insert;
	}
}

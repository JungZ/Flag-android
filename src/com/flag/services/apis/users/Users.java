package com.flag.services.apis.users;

import java.io.IOException;

import com.flag.models.User;
import com.flag.services.apis.FlagClient;

public class Users {
	private FlagClient client;

	public Users(FlagClient client) {
		super();
		this.client = client;
	}

	public Insert insert(User user) throws IOException {
		Insert insert = new Insert(client, user);
		client.initialize(insert);
		return insert;
	}

	public Get get(Long id) throws IOException {
		Get get = new Get(client);
		get.setId(id);
		client.initialize(get);
		return get;
	}
}

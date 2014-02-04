package com.flag.services.apis.users;

import java.io.IOException;

import com.flag.models.RetainForm;
import com.flag.models.UserForm;
import com.flag.services.apis.FlagClient;

public class Users {
	private FlagClient client;

	public Users(FlagClient client) {
		super();
		this.client = client;
	}

	public Insert insert(UserForm userForm) throws IOException {
		Insert insert = new Insert(client, userForm);
		client.initialize(insert);
		return insert;
	}

	public Get get(UserForm userForm) throws IOException {
		Get get = new Get(client, userForm);
		client.initialize(get);
		return get;
	}
	
	public Retain retain(RetainForm retainForm) throws IOException {
		Retain retain = new Retain(client, retainForm);
		client.initialize(retain);
		return retain;
	}
}

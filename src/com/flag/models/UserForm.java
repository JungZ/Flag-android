package com.flag.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class UserForm extends GenericJson {
	@Key
	private String email;
	
	@Key
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

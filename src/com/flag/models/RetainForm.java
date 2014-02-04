package com.flag.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class RetainForm extends GenericJson {
	@Key
	private long id;

	public RetainForm(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

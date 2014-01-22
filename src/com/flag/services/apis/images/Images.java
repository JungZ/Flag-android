package com.flag.services.apis.images;

import java.io.IOException;

import com.flag.models.Image;
import com.flag.services.apis.FlagClient;

public class Images {
	private FlagClient client;

	public Images(FlagClient client) {
		super();
		this.client = client;
	}
	
	public Insert insert(Image image) throws IOException {
		Insert insert = new Insert(client, image);
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

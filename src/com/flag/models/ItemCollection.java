package com.flag.models;

import java.util.List;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

public class ItemCollection extends GenericJson {
	@Key
	private List<Item> items;

	public ItemCollection() {
		super();
	}

	public ItemCollection(List<Item> items) {
		super();
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}

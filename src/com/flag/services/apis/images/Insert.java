package com.flag.services.apis.images;

import com.flag.models.Image;
import com.flag.services.apis.FlagClient;
import com.flag.services.apis.FlagRequest;

public class Insert extends FlagRequest<Image> {
	private static final String REST_PATH = "image";

	protected Insert(FlagClient client, Image image) {
		super(client, "POST", REST_PATH, image, Image.class);
	}

	@Override
	public Insert setAlt(String alt) {
		return (Insert) super.setAlt(alt);
	}

	@Override
	public Insert setFields(String fields) {
		return (Insert) super.setFields(fields);
	}

	@Override
	public Insert setKey(String key) {
		return (Insert) super.setKey(key);
	}

	@Override
	public Insert setOauthToken(String oauthToken) {
		return (Insert) super.setOauthToken(oauthToken);
	}

	@Override
	public Insert setPrettyPrint(Boolean prettyPrint) {
		return (Insert) super.setPrettyPrint(prettyPrint);
	}

	@Override
	public Insert setQuotaUser(String quotaUser) {
		return (Insert) super.setQuotaUser(quotaUser);
	}

	@Override
	public Insert setUserIp(String userIp) {
		return (Insert) super.setUserIp(userIp);
	}
}

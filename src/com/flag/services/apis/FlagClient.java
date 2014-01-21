package com.flag.services.apis;

import java.io.IOException;

import com.flag.services.apis.flags.Flags;
import com.flag.services.apis.shops.Shops;
import com.flag.services.apis.users.Users;
import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.common.base.Preconditions;

public class FlagClient extends AbstractGoogleJsonClient {
	static {
		Preconditions.checkState(GoogleUtils.VERSION.equals("1.13.2-beta"), "You are currently running with version %s of google-api-client. "
				+ "You need version 1.13.2-beta of google-api-client to run version " + "1.13.2-beta of the  library.", GoogleUtils.VERSION);
	}

	public static final String DEFAULT_ROOT_URL = "https://genuine-evening-455.appspot.com/_ah/api/";
	public static final String DEFAULT_SERVICE_PATH = "flagengine/v1/";
	@Deprecated
	public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

	public FlagClient(HttpTransport transport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) {
		super(transport, jsonFactory, DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH, httpRequestInitializer, false);
	}

	public FlagClient(HttpTransport transport, HttpRequestInitializer httpRequestInitializer, String rootUrl, String servicePath,
			JsonObjectParser jsonObjectParser, GoogleClientRequestInitializer googleClientRequestInitializer, String applicationName,
			boolean suppressPatternChecks) {
		super(transport, httpRequestInitializer, rootUrl, servicePath, jsonObjectParser, googleClientRequestInitializer, applicationName, suppressPatternChecks);
	}

	@Override
	public void initialize(AbstractGoogleClientRequest<?> httpClientRequest) throws IOException {
		super.initialize(httpClientRequest);
	}

	public Users users() {
		return new Users(this);
	}
	
	public Flags flags() {
		return new Flags(this);
	}
	
	public Shops shops() {
		return new Shops(this);
	}

	public static final class Builder extends AbstractGoogleJsonClient.Builder {
		public Builder(HttpTransport transport, JsonFactory jsonFactory, HttpRequestInitializer httpRequestInitializer) {
			super(transport, jsonFactory, DEFAULT_ROOT_URL, DEFAULT_SERVICE_PATH, httpRequestInitializer, false);
		}

		@Override
		public FlagClient build() {
			return new FlagClient(getTransport(), getHttpRequestInitializer(), getRootUrl(), getServicePath(), getObjectParser(),
					getGoogleClientRequestInitializer(), getApplicationName(), getSuppressPatternChecks());
		}

		@Override
		public Builder setRootUrl(String rootUrl) {
			return (Builder) super.setRootUrl(rootUrl);
		}

		@Override
		public Builder setServicePath(String servicePath) {
			return (Builder) super.setServicePath(servicePath);
		}

		@Override
		public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
			return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
		}

		@Override
		public Builder setApplicationName(String applicationName) {
			return (Builder) super.setApplicationName(applicationName);
		}

		@Override
		public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
			return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
		}

		public Builder setFlagRequestInitializer(FlagRequestInitializer FlagRequestInitializer) {
			return (Builder) super.setGoogleClientRequestInitializer(FlagRequestInitializer);
		}

		@Override
		public Builder setGoogleClientRequestInitializer(GoogleClientRequestInitializer googleClientRequestInitializer) {
			return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
		}
	}
}

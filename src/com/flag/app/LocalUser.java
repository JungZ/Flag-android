package com.flag.app;

import com.flag.models.User;

public class LocalUser {
	private static User user;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		LocalUser.user = user;
	}
}

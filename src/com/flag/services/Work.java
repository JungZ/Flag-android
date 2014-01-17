package com.flag.services;

import java.io.IOException;

public abstract class Work<T> {
	public abstract T work() throws IOException;
}

package com.flag.services;

import android.os.Handler;
import android.os.Message;

public abstract class ResponseHandler<T> extends Handler {
	protected abstract void onResponse(T response);
	
	@SuppressWarnings("unchecked")
	@Override
	public final void handleMessage(Message m) {
		onResponse((T) m.obj);
	}
}

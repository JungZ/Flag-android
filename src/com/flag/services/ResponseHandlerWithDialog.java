package com.flag.services;

import android.app.Dialog;

public abstract class ResponseHandlerWithDialog<T> extends ResponseHandler<T> {
	private Dialog dialog;

	public ResponseHandlerWithDialog(Dialog dialog) {
		super();
		this.dialog = dialog;
	}
	
	@Override
	protected void onResponse(T response) {
		if (dialog != null)
			dialog.dismiss();
	}
}

package com.flag.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flag.R;
import com.flag.app.LocalUser;
import com.flag.models.User;
import com.flag.models.UserForm;
import com.flag.services.NetworkInter;
import com.flag.services.ResponseHandlerWithDialog;
import com.flag.utils.DialogUtils;

public class LoginActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button btn_login = (Button) findViewById(R.id.btnlogin);
		Button btn_join = (Button) findViewById(R.id.btnNewAccount);
		btn_login.setOnClickListener(this);
		btn_join.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnlogin) {

			EditText edit_email = (EditText) findViewById(R.id.enter_email);
			EditText edit_pw = (EditText) findViewById(R.id.enter_pw);

			String email = edit_email.getText().toString();
			String pw = edit_pw.getText().toString();

			if (blankCheck(email, "e-mail address") && blankCheck(pw, "password")) {
				String pwEnc = com.flag.activities.JoinActivity.encryptSHA256(pw);

				UserForm user0 = new UserForm();
				user0.setEmail(email);
				user0.setPassword(pwEnc);

				NetworkInter.getUser(new ResponseHandlerWithDialog<User>(DialogUtils.getWaitingDialog(this)) {
					@Override
					protected void onResponse(User response) {
						super.onResponse(response);
						if (response == null || response.getId() == 0) {
							Toast.makeText(LoginActivity.this, "please enter a valid account", Toast.LENGTH_SHORT).show();
							return;
						}
						SharedPreferences prefs = getSharedPreferences("MyUser", MODE_PRIVATE);
						SharedPreferences.Editor editor = prefs.edit();
						editor.putLong("id", response.getId());
						editor.commit();
						LocalUser.setUser(response);
						startActivity(new Intent(LoginActivity.this, MapActivity.class));
						finish();
					}
				}, user0);
			}
		} else if (v.getId() == R.id.btnNewAccount) {
			startActivity(new Intent(LoginActivity.this, JoinActivity.class));
		}

		else
			return;
	}

	boolean blankCheck(String blank, String msg) {
		if (blank.equals("")) {
			Toast.makeText(this, "please enter your " + msg, Toast.LENGTH_SHORT).show();
			return false;
		} else
			return true;
	}

}
package com.flag.activities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

public class JoinActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_join);

		Button btn_join = (Button) findViewById(R.id.btnjoin);
		btn_join.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnjoin) {
			EditText edit_email = (EditText) findViewById(R.id.enter_email);
			EditText edit_pw = (EditText) findViewById(R.id.enter_pw);
			EditText edit_pwcheck = (EditText) findViewById(R.id.enter_pwcheck);

			String email = edit_email.getText().toString();
			String pw = edit_pw.getText().toString();
			String pwcheck = edit_pwcheck.getText().toString();

			if (emailCheck(email) && pwCheck(pw, pwcheck)) {

				String pwEnc = encryptSHA256(pw);

				UserForm user0 = new UserForm();
				user0.setEmail(email);
				user0.setPassword(pwEnc);

				NetworkInter.insertUser(new ResponseHandlerWithDialog<User>(DialogUtils.getWaitingDialog(this)) {
					@Override
					protected void onResponse(User response) {
						super.onResponse(response);
						if (response == null || response.getId() == 0)
							return;
						SharedPreferences prefs = getSharedPreferences("MyUser", MODE_PRIVATE);
						SharedPreferences.Editor editor = prefs.edit();
						editor.putLong("id", response.getId());
						editor.commit();
						LocalUser.setUser(response);
						Intent intent = new Intent(JoinActivity.this, MapActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						startActivity(intent);
						finish();
					}
				}, user0);

			} else
				return;
		}
	}

	boolean emailCheck(String email) {
		if (email.equals("")) {
			Toast.makeText(this, "please enter your e-mail address", Toast.LENGTH_SHORT).show();
			return false;
		} else if (!email.matches("[_a-zA-Z0-9-]+@[_a-zA-Z0-9-]+[.]+[_a-zA-Z0-9-]+$")) {
			Toast.makeText(this, "please enter a valid e-mail address", Toast.LENGTH_SHORT).show();
			return false;
		} else
			return true;
	}

	boolean pwCheck(String pw, String pw2) {
		if (pw.equals("")) {
			Toast.makeText(this, "please enter a password", Toast.LENGTH_SHORT).show();
			return false;
		} else if (!pw.equals(pw2)) {
			Toast.makeText(this, "passwords do not match", Toast.LENGTH_SHORT).show();
			return false;
		} else
			return true;

	}

	public static String encryptSHA256(String st) {
		String encryptData = "";
		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			sha.update(st.getBytes());
			byte[] digest = sha.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < digest.length; i++)
				sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
			encryptData = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			encryptData = null;
		}

		return encryptData;

	}

}
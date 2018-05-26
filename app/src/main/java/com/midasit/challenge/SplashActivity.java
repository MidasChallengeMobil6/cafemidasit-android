package com.midasit.challenge;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.midasit.challenge.ui.login.LoginActivity;


public class SplashActivity extends AppCompatActivity {
	private static final String TAG = SplashActivity.class.getSimpleName();

	private static final int REQUEST_CODE_PERMISSION = 1391;
	private static final int DELAY_TIME = 1500;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (checkAndGrantPermission()) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(intent);

					finish();
				}
			}, DELAY_TIME);
		}
	}

	private boolean checkAndGrantPermission() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
				ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_CODE_PERMISSION);
			}

			return false;
		} else {
			return true;
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
		@NonNull int[] grantResults) {
		switch (requestCode) {
			case REQUEST_CODE_PERMISSION:
				if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Log.d(TAG, TAG + ".onRequestPermissionsResult(): permission granted");
					Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
				}

				break;
			default:
				super.onRequestPermissionsResult(requestCode, permissions, grantResults);
				break;
		}
	}
}

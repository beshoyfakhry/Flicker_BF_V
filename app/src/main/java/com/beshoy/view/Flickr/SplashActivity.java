package com.beshoy.view.Flickr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;



/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */

public class SplashActivity extends Activity {

	private final int SPLASH_DISPLAY_LENGTH = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		startAction();
	}
	private void startAction() {


			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
                /* Create an Intent that will start the Menu-Activity. */
					Intent i = new Intent(SplashActivity.this, ImageRequestActivity.class);
					SplashActivity.this.startActivity(i);
					SplashActivity.this.finish();
				}
			}, SPLASH_DISPLAY_LENGTH);
		}
}

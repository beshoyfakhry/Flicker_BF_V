package com.beshoy.view.Flickr;

import Helper.volley.ImageCacheManager;
import Helper.volley.RequestManager;

import android.app.Application;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */

public class AppController extends Application {

	public static final String TAG = AppController.class
			.getSimpleName();

	private RequestQueue mRequestQueue;
	private ImageLoader mImageLoader;

	private static AppController mInstance;

	// cache images api
	public static int DISK_IMAGECACHE_SIZE = 1024 * 1024 * 10;
	public static Bitmap.CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
	public static int DISK_IMAGECACHE_QUALITY = 100;


	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		RequestManager.init(this);
		createImageCache();
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}



	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
	private void createImageCache() {
		ImageCacheManager.getInstance().init(this, this.getPackageCodePath(),
				DISK_IMAGECACHE_SIZE,  DISK_IMAGECACHE_COMPRESS_FORMAT,
				DISK_IMAGECACHE_QUALITY, ImageCacheManager.CacheType.MEMORY);
	}
}

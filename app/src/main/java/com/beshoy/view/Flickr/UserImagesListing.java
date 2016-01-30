package com.beshoy.view.Flickr;

import Helper.utils.Const;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.beshoy.controller.Request.ListUserImagesRequest;
import com.beshoy.controller.Response.GetPhotosResponse;
import com.beshoy.controller.Service.RequestListener;
import com.beshoy.controller.Service.VolleyConnection;
import com.beshoy.view.Adapters.MyRecyclerAdapter;
import com.beshoy.Beans.PhotoObject;

/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */

public class UserImagesListing extends Activity implements RequestListener {

	private static final String TAG = "RecyclerViewExample";
	private List<PhotoObject> feedsList;
	private RecyclerView mRecyclerView;
	private MyRecyclerAdapter adapter;
	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_image);

		mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		progressBar.setVisibility(View.VISIBLE);

		Bundle data=getIntent().getExtras();
		String userId="";
		if(data!=null) {
			  userId = data.getString("userId");
		}

		ListUserImagesRequest request = new ListUserImagesRequest();
		request.setUserId(userId);
		request.setMethod(Const.SearchPhotos);

		new VolleyConnection(this, request,Const.SearchPhotos,
				this, GetPhotosResponse.class);
	}


	@Override
	public void successResponse(Object packet, String apiName) {
		progressBar.setVisibility(View.GONE
		);
		if(apiName.equals(Const.SearchPhotos))
		{
			GetPhotosResponse response=(GetPhotosResponse)packet;
			feedsList=response.getPhotos().getPhoto();
			adapter = new MyRecyclerAdapter(this, feedsList);
			mRecyclerView.setAdapter(adapter);
		}
	}

	@Override
	public void failResponse(String msg) {
		progressBar.setVisibility(View.GONE
		);

	}
}

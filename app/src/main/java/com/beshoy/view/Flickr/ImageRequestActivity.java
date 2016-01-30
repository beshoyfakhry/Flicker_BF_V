package com.beshoy.view.Flickr;

import Helper.utils.Const;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beshoy.controller.Request.SearchImagesRequest;
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
public class ImageRequestActivity extends Activity implements RequestListener {

	private static final String TAG = "RecyclerViewExample";
	private List<PhotoObject> feedsList;
	public RecyclerView mRecyclerView;
	private MyRecyclerAdapter adapter;
	private ProgressBar progressBar;
	private EditText searchEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image);
		init();
		startRequest();
	}
	private void init()
	{
		mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		progressBar.setVisibility(View.VISIBLE);
		searchEditText=(EditText)findViewById(R.id.et_search);
		searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					startRequest();
					return true;
				}
				return false;
			}
		});
	}
	private void startRequest()
	{
		SearchImagesRequest request = new SearchImagesRequest();
		request.setTags(searchEditText.getText().toString());
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

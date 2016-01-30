package com.beshoy.view.Adapters;


/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.beshoy.Beans.PhotoObject;

import java.util.List;

import com.beshoy.view.Flickr.ImageRequestActivity;


import com.beshoy.view.Flickr.R;
import com.beshoy.view.Flickr.UserImagesListing;
import Helper.volley.ImageCacheManager;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private List<PhotoObject> feedItemList;
    private Context mContext;

    public MyRecyclerAdapter(Context context, List<PhotoObject> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int position) {
        PhotoObject feedItem = feedItemList.get(position);


        customViewHolder.imageView.setImageUrl(createImageString(feedItem), ImageCacheManager.getInstance()
                .getImageLoader());
        customViewHolder.imageView.setTag(position);


        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
    }




    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
    private String createImageString(PhotoObject photoObject) {
        //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
        return "https://farm" + photoObject.getFarm() + ".staticflickr.com/" + photoObject.getServer() + "/" + photoObject.getId() + "_" + photoObject.getSecret() + ".jpg";
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        protected NetworkImageView imageView;
        protected TextView textView;


        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (NetworkImageView) view.findViewById(R.id.thumbnail);
            if(mContext instanceof ImageRequestActivity) {
                imageView.setOnClickListener(this);
            }
            this.textView = (TextView) view.findViewById(R.id.title);
        }


        @Override
        public void onClick(View view) {

            NetworkImageView imageView= (NetworkImageView)view;
            String userId=feedItemList.get((Integer)imageView.getTag()).getOwner();
            Bundle b=new Bundle();
            b.putString("userId",userId);
            Intent i=new Intent(mContext, UserImagesListing.class);
            i.putExtras(b);
            mContext.startActivity(i);
        }
    }

}

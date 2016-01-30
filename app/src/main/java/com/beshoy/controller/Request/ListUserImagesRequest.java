package com.beshoy.controller.Request;

import com.google.gson.annotations.SerializedName;

/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */

public class ListUserImagesRequest extends  MethodParent{

@SerializedName("user_id")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}

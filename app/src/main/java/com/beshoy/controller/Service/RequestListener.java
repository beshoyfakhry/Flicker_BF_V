package com.beshoy.controller.Service;

/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */
public interface RequestListener<T> {
	public void successResponse(T packet, String apiName);

	public void failResponse(String msg);
}

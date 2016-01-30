package com.beshoy.controller.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.beshoy.view.Flickr.AppController;

import Helper.utils.ApplicationDialogs;
import Helper.utils.Const;

/**
 * @author Beshoy Fakhry
 * @email beshoyfakhry@gmail.com
 * @contact 01221231268
 */
public class VolleyConnection {
    private String apiName;
    private Activity activity;
    private Object requestObj;
    private RequestListener listner;
    private String tagJsonObject = "tag_json_obj";


    private String TAG = VolleyConnection.class.getSimpleName();
    private Class<?> returnObject;


    public VolleyConnection(Activity activity, Object requestObj,
                            String apiName, RequestListener listner, Class<?> returnObject) {

        this.activity = activity;
        this.requestObj = requestObj;
        this.apiName = apiName;
        this.listner = listner;
        this.returnObject = returnObject;
        connect();
    }

    public void connect() {
        if(!ApplicationDialogs.isConnectionOn(activity))
        {
            ApplicationDialogs.showToast(activity);
        }
        else{

        JSONObject o = new JSONObject();
        Map<String, Object> mm;
        StringBuffer sb=new StringBuffer();
        if (requestObj != null) {
            String data = new Gson().toJson(requestObj);
            try {
                o = new JSONObject(data);
                mm = parseJSONObjectToMap(o);
                sb = new StringBuffer();
                sb.append(Const.Flickr_URL).append("?");
                Iterator it = mm.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    System.out.println(pair.getKey() + " = " + pair.getValue());
                    it.remove();

                    sb.append(pair.getKey()).append("=").append(pair.getValue().equals("") ? null : pair.getValue()).append("&");
                }
                sb.append("nojsoncallback=1");
                sb.append("&format=json");
                sb.append("&api_key=" + Const.ApiKey);

                Log.d("Connection", "Request " + sb.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        StringRequest sr = new StringRequest(sb.toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.toString());
                        try {
                            listner.successResponse(new Gson().fromJson(response.toString().trim(), returnObject), apiName);
                        } catch (Exception e) {
                            listner.failResponse("Connection error");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                listner.failResponse(error.toString());
            }
        });
        AppController.getInstance().addToRequestQueue(sr,
                tagJsonObject);
    }}

    public Map<String, Object> parseJSONObjectToMap(JSONObject jsonObject) throws JSONException {
        Map<String, Object> mapData = new HashMap<String, Object>();
        Iterator<String> keysItr = jsonObject.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = jsonObject.get(key);

            if (value instanceof JSONArray) {
                value = parseJSONArrayToList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = parseJSONObjectToMap((JSONObject) value);
            }
            mapData.put(key, value);
        }
        return mapData;
    }

    public List<Object> parseJSONArrayToList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = parseJSONArrayToList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = parseJSONObjectToMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }
}
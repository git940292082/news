package com.news.news.model;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.news.news.app.App;
import com.news.news.entity.Luck;
import com.news.news.entity.Talk;
import com.news.news.untils.UrlUntils;

public class FindModel {
	public void  loadTalk(final ILoadDataCallback callback){
		final String url=UrlUntils.getTalkUrl();
		StringRequest request=new StringRequest(url,new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				Log.i("talk", url);
				try {
					JSONObject jsonObject=new JSONObject(response);
					int code;
					code = jsonObject.getInt("error_code");
					if(code==0){
						JSONArray arr=jsonObject.getJSONArray("result");
						Gson gson=new Gson();
						List<Talk> talks=gson.fromJson(arr.toString(), new TypeToken<List<Talk>>(){}.getType());
						Talk talk=talks.get(0);
						Log.i("talk", talk.toString());
						callback.ok(talk);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		},new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
		App.getmQueue().add(request);
	}
	public void loadLuck(String name,final ILoadDataCallback callback){
		String url=UrlUntils.getLuckUrl(name);
		Log.i("loadLuck", url);
		StringRequest request=new StringRequest(url,new Listener<String>() {

			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				try {
					JSONObject jsonObject=new JSONObject(response);
					int code = jsonObject.getInt("error_code");
					if(code==0){
						Gson gson=new Gson();
						Luck luck=gson.fromJson(response,Luck.class);
						callback.ok(luck);
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		},new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		});
		App.getmQueue().add(request);
	}
}

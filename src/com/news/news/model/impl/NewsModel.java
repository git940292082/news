package com.news.news.model.impl;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.news.news.app.App;
import com.news.news.entity.news.News;
import com.news.news.model.INewsModel;
import com.news.news.model.NewsCallBack;

import android.util.Log;


public class NewsModel implements INewsModel{

	@Override
	public void getToutiao(final String name,final NewsCallBack callback) {
		String url="http://v.juhe.cn/toutiao/index?type="+name+"&key=9ec8d66e41b5953b4c6ebfe400e21434";
		StringRequest request=new StringRequest(url, new Listener<String>() {

			@Override
			public void onResponse(String response) {
				Gson gson=new Gson();
				News top=gson.fromJson(response, News.class);
				callback.onNewsLoader(top);
				
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				
			}
		});
		App.getmQueue().add(request);
	}
	

}

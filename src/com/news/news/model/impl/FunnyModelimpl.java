package com.news.news.model.impl;

import java.util.List;


import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.news.news.app.App;
import com.news.news.entity.Funny;
import com.news.news.entity.FunnyItem;
import com.news.news.model.CommonCallback;
import com.news.news.model.FunnyModel;
import com.news.news.untils.GlobalConsts;

/**
 * FunnyFragment model层 接口 实现类
 * 
 * @author Shark Z
 * 
 */
public class FunnyModelimpl implements FunnyModel {

	private RequestQueue mQueue;

	/**
	 * 构造方法
	 */
	public FunnyModelimpl() {
		mQueue = App.getmQueue();

	}
	@Override
	public void getAllFunnys(final CommonCallback callback) {

		/**
		 * 1.加载 所有 Funny 的方法 2.使用什么 方式 解析 响应数据呢？？？ Volley 框架-->频繁 小数据 3.在
		 * Application 创建 请求队列 mQueue
		 */

		
//		String url="http://japi.juhe.cn/joke/img/text.from?page=1&pagesize=20&key=038af288ef6adda3c5c52193161e918d";
		
		
		// 1.StringRequest
		StringRequest stringRequest = new StringRequest(
				
				GlobalConsts.URL_FUNNY_FRAGMENT, new Listener<String>() {

					@Override
					public void onResponse(String response) {

//						Log.i("Funny", "response=" + response);
						// 1.通过 日志 -->数据获取成功

						// 2.解析获取到的响应数据
						Gson gson = new Gson();
						Funny resp = gson.fromJson(response, Funny.class);
						// 获取 所有的 Funny 对象集合
						List<FunnyItem> funnys = resp.getResult().getData();

//						Log.i("Funny", "funnys=" + funnys);// 通过日志 -->数据已获得

						// 3.调用回调方法 将数据 传回
						callback.onDataLoaded(funnys);

					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
//						Log.v("VolleyError error", "error=" + error);
					}
				});

		// 2.将请求加入队列
		mQueue.add(stringRequest);
	}

}

package com.news.news.activity;


import com.news.news.R;
import com.news.news.entity.FunnyItem;

import android.os.Bundle;
import android.webkit.WebView;
import android.app.Activity;
import android.content.Intent;

public class FunnyFragment_Item_To_Activity extends Activity {

	private FunnyItem funny;

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_funny_fragment__item__to_);


		//初始化控件 
		webView=(WebView) findViewById(R.id.webView_full_funny);

		//获取 intent funny
		Intent intent = getIntent();
		funny = (FunnyItem) intent.getSerializableExtra("funny");

		//为控件设置 数据

		//		webView 显示 图片 
		webView.loadUrl(funny.getUrl());



	}



}

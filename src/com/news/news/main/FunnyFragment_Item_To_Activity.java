package com.news.news.main;


import org.xutils.x;

import pl.droidsonroids.gif.GifImageView;

import com.news.news.R;
import com.news.news.entity.FunnyItem;

import android.os.Bundle;
import android.webkit.WebView;
import android.app.Activity;
import android.content.Intent;

public class FunnyFragment_Item_To_Activity extends Activity {

	private pl.droidsonroids.gif.GifImageView gifImageView;
	private FunnyItem funny;
	
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_funny_fragment__item__to_);
		
		
		//初始化控件 
		gifImageView=(GifImageView) findViewById(R.id.gifImageView_full_funny);
		webView=(WebView) findViewById(R.id.webView_full_funny);
		
		//获取 intent funny
		Intent intent = getIntent();
		 funny = (FunnyItem) intent.getSerializableExtra("funny");
		
		//为控件设置 数据
		
		x.image().bind(gifImageView, funny.getUrl());
		
		//webView 显示 图片 
		webView.loadUrl(funny.getUrl());
		 
		 
		 
	}

	

}

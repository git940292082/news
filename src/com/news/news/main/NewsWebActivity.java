package com.news.news.main;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.entity.news.Toutiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsWebActivity extends Activity {
	@ViewInject(R.id.wv_news_web)
	WebView wvNews;
	Toutiao news;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_web);
		
		x.view().inject(this);
		
		
		Intent intent=getIntent();
		news=(Toutiao) intent.getSerializableExtra("news");
		wvNews.loadUrl(news.getUrl());
	}

}

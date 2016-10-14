package com.news.news.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.entity.news.Toutiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsWebActivity extends Activity {
	@ViewInject(R.id.wv_news_web)
	WebView wvNews;
	Toutiao news;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_web);

		x.view().inject(this);

		Intent intent = getIntent();
		news = (Toutiao) intent.getSerializableExtra("news");
		String url = news.getUrl();
		if (news == null) {
			url = intent.getStringExtra("url");
		}
		wvNews.loadUrl(url);
		// ·ÀÖ¹Ìø×ªÏµÍ³ä¯ÀÀÆ÷
		wvNews.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
			};
		});
	}

	// »ØÍË
	@Override
	public void onBackPressed() {
		if (wvNews.canGoBack()) {
			wvNews.goBack();
		} else {
			super.onBackPressed();
		}
	}

}

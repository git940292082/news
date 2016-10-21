package com.news.news.activity;

import inject.BaseActivity;
import inject.Onclick;
import inject.ZRLayout;
import inject.ZRView;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.news.news.R;
import com.news.news.app.App;
import com.news.news.entity.news.New;
import com.news.news.entity.news.Toutiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
@ZRLayout(R.layout.activity_news_web)
public class NewsWebActivity extends BaseActivity {
	@ZRView(R.id.wv_news_web)
	WebView wvNews;
	@ZRView(R.id.new_web_btn_collection)
	Button btnCollection;
	@ZRView(R.id.new_web_back)
	ImageView btnBack;
	Toutiao news;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		news = (Toutiao) intent.getSerializableExtra("news");
		url = news.getUrl();
		wvNews.loadUrl(url);
		// 防止跳转系统浏览器
		wvNews.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
			};
		});
		for(int i=0;i<App.collections.size();i++){
			Object object=App.collections.get(i);
			if(object instanceof Toutiao){
				Toutiao toutiao=(Toutiao) object;
				if(toutiao.getUrl().equals(url)){
					btnCollection.setText("已收藏");
					break;
				}
			}
		}
	}
	// 回退
	@Override
	public void onBackPressed() {
		if (wvNews.canGoBack()) {
			wvNews.goBack();
		} else {
			super.onBackPressed();
		}
	}
	@Onclick({R.id.new_web_btn_collection,R.id.new_web_back})
	public void Click(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.new_web_back:
			finish();
			break;
		case R.id.new_web_btn_collection:
			if(btnCollection.getText().toString().equals("已收藏")){
				Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
				return;
			}
			if(App.collection(news)){
				btnCollection.setText("已收藏");
			}
		default:
			break;
		}
	}

}

package com.news.news.activity;


import org.apache.http.Header;
import org.xutils.x;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.news.news.R;
import com.news.news.entity.FunnyItem;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
public class FunnyFragment_Item_To_Activity extends Activity {

	private GifImageView network_gifimageview;
	private AsyncHttpClient asyncHttpClient;

	// private ProgressDialog dialog;

	GifDrawable gbDrawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_funny_fragment__item__to_);
		Intent intent=getIntent();
		FunnyItem funny = (FunnyItem) intent.getSerializableExtra("funny");

		network_gifimageview = (GifImageView) findViewById(R.id.network_gifimageview);
		// SharedPreferencesUtils.setParam(this, "zidong", "");
		String url =funny.getUrl();
		setImage(url);
	}

	public void setImage(final String string) {
		asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.get(string, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub

				GifDrawable drawable = null;
				try {
					if(string.endsWith("gif")){
						drawable = new GifDrawable(arg2);
						drawable.start();
						network_gifimageview.setImageDrawable(drawable);
					}else{
						Bitmap bm=BitmapFactory.decodeByteArray(arg2, 0, arg2.length);
						network_gifimageview.setImageBitmap(bm);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2,
					Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "图片加载失败",
						Toast.LENGTH_SHORT).show();
				// dialog.dismiss();

			}
		});
	}
}

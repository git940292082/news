package com.news.news.activity;

import inject.BaseActivity;
import inject.I;
import inject.Onclick;
import inject.ZRLayout;
import inject.ZRView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.news.news.R;
import com.news.news.R.layout;
import com.news.news.R.menu;
import com.news.news.adapter.CollectionAdapter;
import com.news.news.app.App;
import com.news.news.entity.Video;
import com.news.news.entity.news.Toutiao;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
@ZRLayout(R.layout.activity_collection)
public class CollectionActivity extends BaseActivity{
	@ZRView(R.id.lv_collection)
	PullToRefreshListView collection;
	@ZRView(R.id.collecion_back)
	ImageView btnBack;
	@ZRView(R.id.collection_btn_clear)
	Button btnClear;
	private CollectionAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListener();
		setData();
	}
	private void setListener() {
		// TODO Auto-generated method stub
		collection.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Object object=App.collections.get(arg2);
				Intent intent=new Intent();
				if(object instanceof Toutiao){
					intent.setClass(getApplicationContext(), NewsWebActivity.class);
					intent.putExtra("news",((Toutiao)object));
					startActivity(intent);
				}
			}
		});
	}
	private void setData() {
		// TODO Auto-generated method stub
		adapter=new CollectionAdapter(getApplicationContext(),App.collections);
		collection.setAdapter(adapter);
	}
	@Onclick({R.id.collecion_back,R.id.collection_btn_clear})
	public void Click(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.collecion_back:
			finish();
			break;
		case R.id.collection_btn_clear:
			App.clear();
			adapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
}

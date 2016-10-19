package com.news.news.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.activity.CollectionActivity;
import com.news.news.adapter.CollectionAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class FramMine extends Fragment {
	private View view;
	
	@ViewInject(R.id.btn_shift_nightmode)
	ToggleButton tB;
	@ViewInject(R.id.tv_collection_mine)
	TextView collection;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.fram_mine, null);
			Log.i("123", "FramMine");
			x.view().inject(this,view);
			//初始数据在这里写
			setListener();
		}else{
			((ViewGroup) view.getParent()).removeView(view); 
		}
		return view;
	}
	private void setListener() {
		// TODO Auto-generated method stub
		collection.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),CollectionActivity.class);
				startActivity(intent);
			}
		});
	}
	
}

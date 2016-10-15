package com.news.news.fragment;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ToggleButton;

public class FramMine extends Fragment {
	private View view;
	
	@ViewInject(R.id.btn_shift_nightmode)
	ToggleButton tB;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.fram_mine, null);
			Log.i("123", "FramMine");
			x.view().inject(this,view);
			//初始数据在这里写
			
		}else{
			((ViewGroup) view.getParent()).removeView(view); 
		}
		return view;
	}
	
}

package com.news.news.fragment;
import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.entity.Luck;
import com.news.news.entity.Talk;
import com.news.news.presenter.FindTalkPresenter;
import com.news.news.view.IFindView;

import android.Manifest.permission;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FramFind extends Fragment implements IFindView,OnClickListener{
	
	@ViewInject(R.id.find_jock_tvTalk)
	TextView tvTalk;
	@ViewInject(R.id.find_jock_img)
	ImageView imgHuan;
	private View view;
	FindTalkPresenter presenter;
	public FramFind() {
		// TODO Auto-generated constructor stub
		presenter=new FindTalkPresenter(this);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.fram_find, null);
			Log.i("123", "FramFind");
			x.view().inject(this,view);
			//初始数据在这里写
			setlietener();
			setdata();
			
		}else{
			((ViewGroup) view.getParent()).removeView(view); 
		}
		return view;
	}
	
	private void setlietener() {
		// TODO Auto-generated method stub
		imgHuan.setOnClickListener(this);
	}
	private void setdata() {
		// TODO Auto-generated method stub
		presenter.loadTalk();
		presenter.loadLuck("白羊座");
	}
	@Override
	public void showTalk(Talk talk) {
		// TODO Auto-generated method stub
		tvTalk.setText(talk.getContent());
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.find_jock_img:
			presenter.loadTalk();
			break;
		}
	}
	@Override
	public void showLuck(Luck luck) {
		// TODO Auto-generated method stub
		Log.i("loadLuckloadLuck", luck.toString());
	}
}

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FramFind extends Fragment implements IFindView,OnClickListener,OnItemSelectedListener{
	
	@ViewInject(R.id.find_jock_tvTalk)
	TextView tvTalk;
	@ViewInject(R.id.find_jock_img)
	ImageView imgHuan;
	@ViewInject(R.id.luck_all)
	TextView tvAll;
	@ViewInject(R.id.luck_color)
	TextView tvColor;
	@ViewInject(R.id.luck_health)
	TextView tvHealth;
	@ViewInject(R.id.luck_love)
	TextView tvLove;
	@ViewInject(R.id.luck_number)
	TextView tvNumber;
	@ViewInject(R.id.luck_QFriend)
	TextView tvQFriend;
	@ViewInject(R.id.luck_summary)
	TextView tvSummary;
	@ViewInject(R.id.luck_luck_sp)
	Spinner sp;
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
		sp.setOnItemSelectedListener(this);
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
		tvAll.setText(luck.getAll()+"");
		tvColor.setText(luck.getColor()+"");
		tvHealth.setText(luck.getHealth()+"");
		tvLove.setText(luck.getLove()+"");
		tvNumber.setText(luck.getNumber()+"");
		tvQFriend.setText(luck.getQFriend()+"");
		tvSummary.setText(luck.getSummary()+"");
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String data = (String)sp.getItemAtPosition(arg2);
		presenter.loadLuck(data);
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}

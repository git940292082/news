package com.news.news.fragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.activity.NewsWebActivity;
import com.news.news.adapter.ImgPageAdapter;
import com.news.news.adapter.ImgPageAdapter.ImageCycleViewListener;
import com.news.news.entity.Luck;
import com.news.news.entity.Talk;
import com.news.news.entity.news.News;
import com.news.news.entity.news.Toutiao;
import com.news.news.model.INewsModel;
import com.news.news.model.NewsCallBack;
import com.news.news.model.impl.NewsModel;
import com.news.news.presenter.FindTalkPresenter;
import com.news.news.ui.CircleImageView;
import com.news.news.view.IFindView;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.Tencent;

import android.Manifest.permission;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FramFind extends Fragment implements IFindView,OnClickListener,OnItemSelectedListener,NewsCallBack{

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
	@ViewInject(R.id.find_imgHandle)
	ViewPager imageHandle;
	@ViewInject(R.id.find_tv_hander)
	TextView tv;
	private View view;
	FindTalkPresenter presenter;
	NewsModel model;
	private News news;
	public FramFind() {
		// TODO Auto-generated constructor stub
		presenter=new FindTalkPresenter(this);
		model=new NewsModel();
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
		model.getToutiao("top", this);
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
	@Override
	public void onNewsLoader(Object object) {
		// TODO Auto-generated method stub
		if(object==null){
			return;
		}
		news=(News) object;
		List<Toutiao> lists=new ArrayList<Toutiao>();
		for(int i=0;i<5;i++){
			lists.add(news.getResult().getData().get(i));
		}
		ImgPageAdapter adapter=new ImgPageAdapter(imageHandle,getActivity(), lists,tv, imageCycleViewListener);
		imageHandle.setAdapter(adapter);
		imageHandle.setCurrentItem(100,false);
	}
	ImageCycleViewListener imageCycleViewListener=new ImageCycleViewListener() {
		@Override
		public void onImageClick(Toutiao toutiao) {
			// TODO Auto-generated method stub
			Intent intent=new Intent(getActivity(),NewsWebActivity.class);
			intent.putExtra("news",toutiao);
			startActivity(intent);
		}
	};
}

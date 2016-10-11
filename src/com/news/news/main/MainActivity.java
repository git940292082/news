package com.news.news.main;

import java.util.ArrayList;

import com.news.news.R;
import com.news.news.fragment.FramFind;
import com.news.news.fragment.FramFunny;
import com.news.news.fragment.FramMine;
import com.news.news.fragment.FramNews;
import com.news.news.fragment.FramVideo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
public class MainActivity extends FragmentActivity implements OnCheckedChangeListener{

	private RadioGroup rg;
	private ViewPager framPage;
	private TextView tvTitle;
	private ArrayList<Fragment> Fragmengs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loadView();
		
		loadListener();
		
		loadData();
	}
	private void loadView() {
		// TODO Auto-generated method stub
		rg=(RadioGroup)findViewById(R.id.main_groaup);
		tvTitle=(TextView)findViewById(R.id.main_tv_title);
		framPage=(ViewPager)findViewById(R.id.main_fram);
	}
	private void loadListener() {
		// TODO Auto-generated method stub
		rg.setOnCheckedChangeListener(this);
	}
	private void loadData() {
		// TODO Auto-generated method stub
		Fragmengs=new ArrayList<Fragment>();
		Fragmengs.add(new FramNews());
		Fragmengs.add(new FramFunny());
		Fragmengs.add(new FramVideo());
		Fragmengs.add(new FramFind());
		Fragmengs.add(new FramMine());
		PageAdapter pageAdapter=new PageAdapter(getSupportFragmentManager());
		framPage.setOffscreenPageLimit(1);
		framPage.setAdapter(pageAdapter);
	}
	public class PageAdapter  extends FragmentPagerAdapter{
		public PageAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}
		@Override
		
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return Fragmengs.get(arg0);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Fragmengs.size();
		}
		
	}
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.radio1:
			framPage.setCurrentItem(0,false);
			tvTitle.setText("  新闻  ");
			break;
		case R.id.radio2:
			framPage.setCurrentItem(1,false);
			tvTitle.setText("  娱乐  ");
			break;
		case R.id.radio3:
			framPage.setCurrentItem(2,false);
			tvTitle.setText("  视频  ");
			break;
		case R.id.radio4:
			framPage.setCurrentItem(3,false);
			tvTitle.setText("  发现  ");
			break;
		case R.id.radio5:
			framPage.setCurrentItem(4,false);
			tvTitle.setText("  我的  ");
			break;
		}
	}
	long currentTime;
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		currentTime=System.currentTimeMillis()-currentTime;
		if(currentTime<2000){
			finish();
		}else{
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			currentTime=System.currentTimeMillis();
		}
	}
}

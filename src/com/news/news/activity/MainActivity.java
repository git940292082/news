package com.news.news.activity;

import java.util.ArrayList;

import com.news.news.R;
import com.news.news.fragment.FramFind;
import com.news.news.fragment.FramFunny;
import com.news.news.fragment.FramMine;
import com.news.news.fragment.FramNews;
import com.news.news.fragment.FramVideo;
import com.zxing.android.CaptureActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
public class MainActivity extends FragmentActivity implements OnCheckedChangeListener,OnClickListener{

	private RadioGroup rg;
	private ViewPager framPage;
	private TextView tvTitle;
	private ArrayList<Fragment> Fragmengs;
	private ImageButton IBtnJia;
	PopupWindow popuWindow;
	private View view;
	private Button btnShao;
	private Button btnBack;
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
		IBtnJia=(ImageButton) findViewById(R.id.main_itbn_jia);
		view=View.inflate(this, R.layout.windows, null);
		btnShao=(Button)view.findViewById(R.id.windos_shao);
		btnBack=(Button)view.findViewById(R.id.windos_back);
	}
	private void loadListener() {
		// TODO Auto-generated method stub
		rg.setOnCheckedChangeListener(this);
		IBtnJia.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(popuWindow==null){
					show();
				}else{
					popuWindow.dismiss();
					popuWindow=null;
				}
			}
		});
		btnShao.setOnClickListener(this);
		btnBack.setOnClickListener(this);
	}
	protected void show() {
		popuWindow=new PopupWindow(view,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		popuWindow.showAsDropDown(IBtnJia, 0, 15);

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
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.windos_back:
			popuWindow.dismiss();
			popuWindow=null;
			finish();
			break;
		case R.id.windos_shao:
			popuWindow.dismiss();
			popuWindow=null;
			startActivity(new Intent(this,CaptureActivity.class));
			break;
		}
	}
}

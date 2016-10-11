package com.news.news.fragment;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.fragment.news.CaijingFragment;
import com.news.news.fragment.news.GuojiFragment;
import com.news.news.fragment.news.GuoneiFragment;
import com.news.news.fragment.news.JunshiFragment;
import com.news.news.fragment.news.KejiFragment;
import com.news.news.fragment.news.ShehuiFragment;
import com.news.news.fragment.news.ShishangFragment;
import com.news.news.fragment.news.TiyuFragment;
import com.news.news.fragment.news.ToutiaoFragment;
import com.news.news.fragment.news.YuleFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FramNews extends Fragment {
	@ViewInject(R.id.vp_news_framents)
	private ViewPager vpFraments;
	@ViewInject(R.id.rg_news)
	private RadioGroup rgNews;
	@ViewInject(R.id.rb_news_toutiao)
	private RadioButton rbToutiao; // 头条
	@ViewInject(R.id.rb_news_keji)
	private RadioButton rbKeji; // 科技
	@ViewInject(R.id.rb_news_yule)
	private RadioButton rbYule; // 娱乐
	@ViewInject(R.id.rb_news_tiyu)
	private RadioButton rbTiyu; // 体育
	@ViewInject(R.id.rb_news_caijing)
	private RadioButton rbCaijing; // 财经
	@ViewInject(R.id.rb_news_shishang)
	private RadioButton rbShishang; // 时尚
	@ViewInject(R.id.rb_news_junshi)
	private RadioButton rbJunshi; // 军事
	@ViewInject(R.id.rb_news_shehui)
	private RadioButton rbShehui; // 社会
	@ViewInject(R.id.rb_news_guonei)
	private RadioButton rbGuonei; // 国内
	@ViewInject(R.id.rb_news_guoji)
	private RadioButton rbGuoji; // 国际

	// 新闻Frag
	private List<Fragment> newsFragments;
	private View view;

	private Newsprageadapter newsprageadapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.fram_news, null);
			// x注解
			x.view().inject(this, view);
			// FragmentAdapter
			setFragmentAdapter();
			// 监听器
			setListeners();
		} else {
			((ViewGroup) view.getParent()).removeView(view);
		}
		return view;
	}

	private void setFragmentAdapter() {
		newsFragments = new ArrayList<Fragment>();
		newsFragments.add(new ToutiaoFragment());
		newsFragments.add(new ShehuiFragment());
		newsFragments.add(new GuoneiFragment());
		newsFragments.add(new GuojiFragment());
		newsFragments.add(new KejiFragment());
		newsFragments.add(new YuleFragment());
		newsFragments.add(new JunshiFragment());
		newsFragments.add(new TiyuFragment());
		newsFragments.add(new CaijingFragment());
		newsFragments.add(new ShishangFragment());
		newsprageadapter = new Newsprageadapter(getFragmentManager());
		vpFraments.setAdapter(newsprageadapter);

	}

	class Newsprageadapter extends FragmentPagerAdapter {

		public Newsprageadapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return newsFragments.get(arg0);
		}
		@Override
		public int getCount() {
			return newsFragments.size();
		}

	}

	// 监听
	private void setListeners() {
		rgNews.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_news_toutiao:
					vpFraments.setCurrentItem(0);
					break;
				case R.id.rb_news_shehui:
					vpFraments.setCurrentItem(1);
					break;
				case R.id.rb_news_guonei:
					vpFraments.setCurrentItem(2);
					break;
				case R.id.rb_news_guoji:
					vpFraments.setCurrentItem(3);
					break;
				case R.id.rb_news_keji:
					vpFraments.setCurrentItem(4);
					break;
				case R.id.rb_news_yule:
					vpFraments.setCurrentItem(5);
					break;
				case R.id.rb_news_junshi:
					vpFraments.setCurrentItem(6);
					break;
				case R.id.rb_news_tiyu:
					vpFraments.setCurrentItem(7);
					break;
				case R.id.rb_news_caijing:
					vpFraments.setCurrentItem(8);
					break;
				case R.id.rb_news_shishang:
					vpFraments.setCurrentItem(9);
					break;

				}

			}
		});
		vpFraments.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					rbToutiao.setChecked(true);
					break;
				case 1:
					rbShehui.setChecked(true);
					break;
				case 2:
					rbGuonei.setChecked(true);
					break;
				case 3:
					rbGuoji.setChecked(true);
					break;
				case 4:
					rbKeji.setChecked(true);
					break;
				case 5:
					rbYule.setChecked(true);
					break;
				case 6:
					rbJunshi.setChecked(true);
					break;
				case 7:
					rbTiyu.setChecked(true);
					break;
				case 8:
					rbCaijing.setChecked(true);
					break;
				case 9:
					rbShishang.setChecked(true);
					break;

				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

	}
}

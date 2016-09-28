package com.news.news.fragment;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.fragment.news.NewsFragment;
import com.news.news.fragment.news.ToutiaoFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
		newsFragments.add(new NewsFragment("shehui"));
		newsFragments.add(new NewsFragment("guonei"));
		newsFragments.add(new NewsFragment("guoji"));
		newsFragments.add(new NewsFragment("yule"));
		newsFragments.add(new NewsFragment("tiyu"));
		newsFragments.add(new NewsFragment("junshi"));
		newsFragments.add(new NewsFragment("keji"));
		newsFragments.add(new NewsFragment("caijing"));
		newsFragments.add(new NewsFragment("shishang"));
		

	}

	private void setListeners() {

	}
}

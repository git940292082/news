package com.news.news.fragment;
import java.util.ArrayList;
import java.util.List;
import com.news.news.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FramNews extends Fragment {
	private ViewPager vpFraments;
	private RadioGroup rgNews;

	private RadioButton rbRedian; // 热点
	private RadioButton rbToutiao; // 头条
	private RadioButton rbKeji; // 科技
	private RadioButton rbYule; // 娱乐
	private RadioButton rbYouxi; // 游戏
	private RadioButton rbYingshi; // 影视
	private RadioButton rbTiyu; // 体育
	private RadioButton rbCaijing; // 财经
	private RadioButton rbQiche; // 汽车
	private RadioButton rbShishang; // 时尚
	private RadioButton rbJunshi; // 军事
	private RadioButton rbLishi; // 历史
	private RadioButton rbCaipiao; // 彩票
	private RadioButton rbLuntan; // 论坛

	//新闻Frag
	private List<Fragment> newsFragments;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fram_news, null);
		// 初始化
		setViews(view);

		// FragmentAdapter
		setFragmentAdapter();

		// 监听器
		setListeners();

		return view;
	}

	private void setFragmentAdapter() {
		newsFragments=new ArrayList<Fragment>();
	}

	private void setListeners() {

	}

	private void setViews(View view) {
		// ViewPager
		vpFraments = (ViewPager) view.findViewById(R.id.vp_news_framents);

		// Radio
		rgNews = (RadioGroup) view.findViewById(R.id.rg_news);
		rbRedian = (RadioButton) view.findViewById(R.id.rb_news_redian);
		rbToutiao = (RadioButton) view.findViewById(R.id.rb_news_toutiao);
		rbKeji = (RadioButton) view.findViewById(R.id.rb_news_keji);
		rbYule = (RadioButton) view.findViewById(R.id.rb_news_yule);
		rbYouxi = (RadioButton) view.findViewById(R.id.rb_news_youxi);
		rbYingshi = (RadioButton) view.findViewById(R.id.rb_news_yingshi);
		rbTiyu = (RadioButton) view.findViewById(R.id.rb_news_tiyu);
		rbCaijing = (RadioButton) view.findViewById(R.id.rb_news_caijing);
		rbQiche = (RadioButton) view.findViewById(R.id.rb_news_qiche);
		rbShishang = (RadioButton) view.findViewById(R.id.rb_news_shishang);
		rbJunshi = (RadioButton) view.findViewById(R.id.rb_news_junshi);
		rbLishi = (RadioButton) view.findViewById(R.id.rb_news_lishi);
		rbCaipiao = (RadioButton) view.findViewById(R.id.rb_news_caipiao);
		rbLuntan = (RadioButton) view.findViewById(R.id.rb_news_luntan);

	}
}

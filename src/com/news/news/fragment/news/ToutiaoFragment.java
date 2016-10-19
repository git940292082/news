package com.news.news.fragment.news;


import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.activity.NewsWebActivity;
import com.news.news.adapter.NewsAdapter;
import com.news.news.entity.news.News;
import com.news.news.entity.news.Toutiao;
import com.news.news.model.INewsModel;
import com.news.news.model.NewsCallBack;
import com.news.news.model.impl.NewsModel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ToutiaoFragment extends Fragment {
	private View view;
	private News newTop;
	private NewsAdapter adapter;
	private INewsModel model;

	@ViewInject(R.id.lv_news_list)
	ListView lvNews;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.news_list, null);
			// x×¢½â
			x.view().inject(this, view);
			model = new NewsModel();
			newTop = new News();
			setadapter();
			setListeners();
		} else {
			((ViewGroup) view.getParent()).removeView(view);
		}
		return view;
	}

	private void setListeners() {
		lvNews.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toutiao news = newTop.getResult().getData().get(position);
				Intent intent = new Intent(getActivity(), NewsWebActivity.class);
				intent.putExtra("news", news);
				startActivity(intent);
			}
		});
	}

	private void setadapter() {
		String top = "top";
		model.getToutiao(top, new NewsCallBack() {

			@Override
			public void onNewsLoader(Object object) {
				newTop = (News) object;
				adapter = new NewsAdapter(getActivity(), newTop.getResult().getData());
				lvNews.setAdapter(adapter);
			}
		});

	}

}

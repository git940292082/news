package com.news.news.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.news.news.R;
import com.news.news.activity.FunnyFragment_Item_To_Activity;
import com.news.news.adapter.FunnyAdapter;
import com.news.news.entity.FunnyItem;
import com.news.news.presenter.FunnyPresenterimpl;
import com.news.news.view.FunnyView;

/**
 * 搞笑栏 趣图、段子
 * 
 * @author SharkZ 2016-9-25
 * 
 */

// @ContentView(R.layout.fram_funny)
public class FramFunny extends Fragment implements FunnyView {

	private View view;
	private FunnyPresenterimpl presenter;
	private List<FunnyItem> funnys;

	// @ViewInject(R.id.lv_fram_funny)
	private ListView listView;

	private FunnyAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (view == null) {

			// 1.View
			view = inflater.inflate(R.layout.fram_funny, null);
			Log.i("123", "FramFunny");

			listView = (ListView) view.findViewById(R.id.lv_fram_funny);

			// //xUtils
			// x.view().inject(getActivity());

			// 2.创建 presenter 对象
			presenter = new FunnyPresenterimpl(this);
			// --组合
			presenter.getAllFunnys();

			// 3.设置监听器 点击每一项Item 都回跳转到 一个现实完整信息的 Activity
			setListener();

		} else {
			((ViewGroup) view.getParent()).removeView(view);

			//初始数据在这里写
			
		}
		return view;
	}

	//-------------------------------------------------------------------------------------------------------

	/**
	 * 各种监听大法
	 */
	private void setListener() {

		/**
		 * 点击每一项跳转到一个Activity 现实完整的 Funny 信息
		 */
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				FunnyItem funny = funnys.get(position);

				Intent intent = new Intent(getActivity(),
						FunnyFragment_Item_To_Activity.class);
				intent.putExtra("funny", funny);

				startActivity(intent);

			}
		});

		/**
		 * 为listview 设置 上啦 刷新
		 */
		listView.setOnScrollListener(new OnScrollListener() {

			boolean isBottom=false;
			boolean  isRequest=false;

			// 当滑动转态改变的时候
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

				switch (scrollState) {

				case OnScrollListener.SCROLL_STATE_IDLE://空闲的时候
					//判断
					if(isBottom&&!isRequest){

						isRequest=true;//重复发送请求

						//调用presenter层重新加载数据
						//						presenter.getAllFunnys(this.funnys.size(),20);

						/**
						 * 1.滑动到底部 重新发请求
						 * 2.将获取到的 集合 全部加入到 旧集合中
						 * 这里加入的是后面新请求的数据
						 * funnys.addAll(funnys);
						 * size()是之前的2倍
						 * 
						 * 3.更新 adapter
						 * adapter.notifyDataSetChanged();
						 * 
						 * 4.isRequest=true;
						 * 
						 * 5.在 请求数据的业务逻辑中 可以加入progressBar
						 * 提醒用户正在更细数据
						 * 
						 */





					}

					break;
				case OnScrollListener.SCROLL_STATE_FLING://快速滑动
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://动的时候
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view,
					int firstVisibleItem,//第一个可见的item的position
					int visibleItemCount,//可见的item的数量
					int totalItemCount) {//总共有多少个item

				if(firstVisibleItem+visibleItemCount==totalItemCount){
					//滑到底了
					isBottom=true;
				}else{
					isBottom=false;
				}

			}
		});

	}
	//-------------------------------------------------------------------------------------------------------





	@Override
	public void showFunnyTextList(List<FunnyItem> funnys) {

		// 1.保存数据
		this.funnys = funnys;
		Log.i("funnys-->fragment", "funnys=" + funnys);// 通过日志发现-->数据以传递过来 接下来可以
		// 设置adapter

		// 2.设置Adapter
		/**
		 * 传入 context funnys listview-->异步加载图片 -->需要 adapter 模板
		 */
		adapter = new FunnyAdapter(getActivity(), funnys, listView);
		// --
		listView.setAdapter(adapter);

	}
}

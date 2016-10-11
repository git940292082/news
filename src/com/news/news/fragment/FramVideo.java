package com.news.news.fragment;
import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.news.news.R;
import com.news.news.adapter.VideoAdapter;
import com.news.news.entity.Video;
import com.news.news.model.IVideoCallback;
import com.news.news.model.VideoModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FramVideo extends Fragment {
	private View view;
	private PullToRefreshListView lv;
	private VideoAdapter adapter;
	private List<Video> Videos=new ArrayList<Video>();
	private boolean isResing;
	protected boolean isBotton;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.fram_video, null);
			Log.i("123", "FramVideo");
			loadView();
			loadListener();
			loadData();
		}else{
			
			((ViewGroup) view.getParent()).removeView(view); 
		}
		
		return view;
	}
	private void loadView() {
		// TODO Auto-generated method stub
		lv=(PullToRefreshListView)view.findViewById(R.id.fram_video_lv);
		ILoadingLayout startLabelse = lv.getLoadingLayoutProxy(true,false);
		startLabelse.setPullLabel("下拉刷新");// 刚下拉时，显示的提示
		startLabelse.setRefreshingLabel("数据加载中...");// 刷新时
		startLabelse.setReleaseLabel("松开加载数据");// 下来达到一定距离时，显示的提示
		// 设置列表内容
	}
	private void loadListener() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
			}
		});
		lv.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if(scrollState==OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
					if(isBotton&&isResing){
						isResing=false;
						Log.i("123", "到底了"+Videos.size());
						VideoModel model=new VideoModel();
						model.loadVideo(Videos.size(), new IVideoCallback() {
							@Override
							public void showVideo(List<Video> videos) {
								// TODO Auto-generated method stub
								isResing=true;
								Videos.addAll(videos);
								adapter.notifyDataSetChanged();
							}
						});					
					}
				}
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				if(firstVisibleItem+visibleItemCount==totalItemCount){
					isBotton=true;
				}else{
					isBotton=false;
				}
			}
		});
		lv.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
				loadData();
			}
		});
	}
	private void loadData() {
		// TODO Auto-generated method stub
		VideoModel model = new VideoModel();
		isResing=false;
		model.loadVideo(0, new IVideoCallback() {
			@Override
			public void showVideo(List<Video> videos) {
				// TODO Auto-generated method stub
				Videos=videos;
				adapter=new VideoAdapter(getActivity(), videos);
				isResing=true;
				lv.setAdapter(adapter);
				lv.onRefreshComplete();
			}
		});
	}
}

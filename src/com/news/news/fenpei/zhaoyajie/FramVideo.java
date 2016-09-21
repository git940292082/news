package com.news.news.fenpei.zhaoyajie;
import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageButton;
import pl.droidsonroids.gif.GifImageView;

import com.news.news.R;
import com.news.news.adapter.VideoAdapter;
import com.news.news.entity.Video;
import com.news.news.fenpei.zhaoyajie.model.IVideoCallback;
import com.news.news.fenpei.zhaoyajie.model.VideoModel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FramVideo extends Fragment {
	private View view;
	private ListView lv;
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
		lv=(ListView)view.findViewById(R.id.fram_video_lv);
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
						Log.i("123", "µ½µ×ÁË"+Videos.size());
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
				View v=LayoutInflater.from(getActivity()).inflate(R.layout.fram_find, null);
				lv.addHeaderView(v);
				lv.setAdapter(adapter);
				
			}
		});
	}
}

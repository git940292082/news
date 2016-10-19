package com.news.news.adapter.Holder;

import inject.ZRView;

import com.news.news.R;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoItemView {
	@ZRView(R.id.video_rl_visi)
	public Button btRlVisi;
	@ZRView(R.id.video_news_tv_title)
	public TextView tvTitle;
	@ZRView(R.id.video_vv)
	public VideoView vvVideo;
	@ZRView(R.id.video_play)
	public ImageView imPlay;
	@ZRView(R.id.video_nowtime)
	public TextView tvNowTime;
	@ZRView(R.id.video_sumtime)
	public TextView tvSumTime;
	@ZRView(R.id.video_seekbar)
	public SeekBar skVideo;
	@ZRView(R.id.video_cache)
	public ProgressBar pvCache;
	@ZRView(R.id.video_rl_bar)
	public RelativeLayout rlBar;
	@ZRView(R.id.video_bg)
	public ImageView imgBg;
	@ZRView(R.id.video_fangda)
	public ImageView fangda;
	@ZRView(R.id.video_collection)
	public Button btnCollection;
	public boolean isload;
	public boolean isplay;
}

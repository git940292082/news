package com.news.news.adapter;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.news.news.R;
import com.news.news.activity.VideoPlayActivity;
import com.news.news.adapter.Holder.VideoItemView;
import com.news.news.app.App;
import com.news.news.entity.Video;
import com.news.news.untils.BitmapUtils;
import com.news.news.untils.DateTimeUtils;

public class VideoLoad {
	public static VideoItemView holders;
	public static void load(final VideoItemView holder, final Video video) {
		// TODO Auto-generated method stub
		holder.tvTitle.setText(video.getTitle());
		holder.imgBg.setImageBitmap(null);
		holder.imPlay.setVisibility(View.VISIBLE);
		holder.pvCache.setVisibility(View.GONE); 
		holder.imgBg.setVisibility(View.VISIBLE);
		holder.rlBar.setVisibility(View.GONE);
		holder.vvVideo.stopPlayback();
		holder.imPlay.setImageResource(R.drawable.ic_video_play);
		try {
			BitmapUtils.loadBitmap(video.getCover(), holder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		holder.imPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!holder.isplay){
					if(holders!=null){
						holders.imPlay.setVisibility(View.VISIBLE);
						holders.imPlay.setImageResource(R.drawable.ic_video_play);
						holders.pvCache.setVisibility(View.GONE); 
						holders.imgBg.setVisibility(View.VISIBLE);
						holders.vvVideo.stopPlayback();
						holders.isplay=false;
						holders.isload=false;
					}

					holder.imPlay.setImageResource(R.drawable.ic_video_pause);
					holder.imPlay.setVisibility(View.GONE); 
					holder.pvCache.setVisibility(View.VISIBLE); 
					String url=video.getMp4Hd_url();
					if(url==null||url.equals("")||url.equals("null")){
						url=video.getMp4_url();
					}
					holder.isplay=true;
					Uri uri=Uri.parse(url);
					// TODO Auto-generated method stub
					holder.vvVideo.setVideoURI(uri);
					holder.vvVideo.requestFocus(); 
					holder.vvVideo.start();
					holders=holder;
				}else{
					if(holder.vvVideo.isPlaying()){
						holder.vvVideo.pause();
						holder.imPlay.setImageResource(R.drawable.ic_video_play);
					}else{
						holder.vvVideo.start();
						holder.imPlay.setImageResource(R.drawable.ic_video_pause);

					}

				}
			}
		});
		holder.btRlVisi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(holder.isload){
					if(holder.rlBar.getVisibility()==View.GONE){
						holder.rlBar.setVisibility(View.VISIBLE);
						holder.imPlay.setVisibility(View.VISIBLE);

					}else{
						holder.rlBar.setVisibility(View.GONE);
						holder.imPlay.setVisibility(View.GONE);
					}
				}
			}
		});
		holder.vvVideo.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				holder.pvCache.setVisibility(View.GONE); 
				holder.imgBg.setVisibility(View.GONE);
				holder.isload=true;
				holder.tvSumTime.setText(DateTimeUtils.getDateFormat(holder.vvVideo.getDuration()));
				holder.skVideo.setMax(holder.vvVideo.getDuration());
				// TODO Auto-generated method stub
				mp.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
					int currentPosition, duration;
					@Override
					public void onBufferingUpdate(MediaPlayer mp, int percent) {
						// TODO Auto-generated method stub
						// 获得当前播放时间和当前视频的长度
						currentPosition = holder.vvVideo.getCurrentPosition();
						duration = holder.vvVideo.getDuration(); 
						// 设置进度条的主要进度，表示当前的播放时间
						holder.tvNowTime.setText(DateTimeUtils.getDateFormat(currentPosition));
						holder.skVideo.setProgress(currentPosition);
						// 设置进度条的次要进度，表示视频的缓冲进度
						percent=percent*duration/100;
						holder.skVideo.setSecondaryProgress(percent);
					}
				});
			}
		});
		holder.skVideo.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				holder.vvVideo.seekTo(seekBar.getProgress());
				holder.vvVideo.start();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				// TODO Auto-generated method stub
				holder.tvNowTime.setText(DateTimeUtils.getDateFormat(progress));
			}
		});
		holder.fangda.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new  Intent(App.context,VideoPlayActivity.class);
				String url=video.getMp4Hd_url();
				if(url==null||url.equals("")||url.equals("null")){
					url=video.getMp4_url();
				}
				intent.putExtra("url", url);
				intent.putExtra("time",holder.vvVideo.getCurrentPosition());
				holder.vvVideo.pause();
				holder.imPlay.setImageResource(R.drawable.ic_video_play);
				holder.imgBg.setVisibility(View.VISIBLE);
				App.context.startActivity(intent);
			}
		});
		holder.btnCollection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(App.collection(video)){
					holder.btnCollection.setText("已收藏");
				}
			}
		});
	}
}

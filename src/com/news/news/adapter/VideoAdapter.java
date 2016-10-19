package com.news.news.adapter;

import inject.I;

import java.util.List;

import org.xutils.x;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.VideoView;

import com.news.news.R;
import com.news.news.activity.VideoPlayActivity;
import com.news.news.adapter.Holder.VideoItemView;
import com.news.news.app.App;
import com.news.news.entity.Video;
import com.news.news.entity.news.Toutiao;
import com.news.news.untils.BitmapUtils;
import com.news.news.untils.DateTimeUtils;
public class VideoAdapter  extends MyAdapter<Video> {

	public VideoAdapter(Context context, List<Video> data) {
		super(context, data);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View layout, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Video video = getData().get(position);
		VideoItemView holder=null;
		if(layout==null){
			layout=getInflater().inflate(R.layout.video_item, null);
			holder=new VideoItemView();
			I.invect(holder, layout);
			layout.setTag(holder);
		}else{
			holder=(VideoItemView) layout.getTag();
		}
		VideoLoad.load(holder,video);
		for(int i=0;i<App.collections.size();i++){
			Object object=App.collections.get(i);
			holder.btnCollection.setText("ÊÕ²Ø");
			if(object instanceof Video){
				Video video1=(Video) object;
				if(video.getTitle().equals(video1.getTitle())){
					holder.btnCollection.setText("ÒÑÊÕ²Ø");
					break;
				}
			}
		}
		return layout;
	}
}

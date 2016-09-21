package com.news.news.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.news.news.entity.Video;
import com.news.news.untils.HttpUntils;
import com.news.news.untils.JsonParser;
import com.news.news.untils.UrlUntils;

import android.os.AsyncTask;
import android.util.Log;
public class VideoModel {
	public void  loadVideo(final int startIndex,final IVideoCallback videoCallback) { 
		AsyncTask<String, String, List<Video>> asy=new AsyncTask<String, String, List<Video>>(){
			@Override
			protected List<Video> doInBackground(String... params) {
				// TODO Auto-generated method stub
				try {
					String url=UrlUntils.getVideoUrl(startIndex);
					InputStream in=HttpUntils.getInputStream(url);
					String js=HttpUntils.intoString(in);
					List<Video> videos=JsonParser.getVideo(js);
					return videos;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(List<Video> result) {
				// TODO Auto-generated method stub
				if(result==null){
					result=new ArrayList<Video>();
				}
				Log.i("123", result.toString());
				videoCallback.showVideo(result);
			}
		};
		asy.execute();
	}
}

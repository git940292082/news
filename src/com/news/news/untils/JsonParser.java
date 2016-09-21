package com.news.news.untils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.news.news.entity.Video;

public class JsonParser {
	public static List<Video> getVideo(String js) throws Exception{
		List<Video> videos=new ArrayList<Video>();
		JSONObject json=new JSONObject(js);
		JSONArray jsr=json.getJSONArray("V9LG4B3A0");
		for(int i=0;i<jsr.length();i++){
			JSONObject jo=jsr.getJSONObject(i);
			Video video=new Video(
					jo.getString("topicImg"),
					jo.getString("videosource"), 
					jo.getString("mp4Hd_url"), 
					jo.getString("topicDesc"),
					jo.getString("cover"), 
					jo.getString("title"),
					jo.getString("mp4_url"), 
					jo.getString("ptime"), 
					jo.getString("topicName"));
			videos.add(video);
		}
		return videos;
		
	}
}

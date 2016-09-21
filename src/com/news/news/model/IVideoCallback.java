package com.news.news.model;

import java.util.List;

import com.news.news.entity.Video;

public interface IVideoCallback {
	public void showVideo(List<Video> videos);
}

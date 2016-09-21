package com.news.news.untils;

public class UrlUntils {
	public static String getVideoUrl(int fromIndex){
		String url="http://c.m.163.com/nc/video/list/V9LG4B3A0/y/"+fromIndex+"-"+(fromIndex+20)+".html";
		return url;
	}
}

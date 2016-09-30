package com.news.news.untils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlUntils {
	public static String getVideoUrl(int fromIndex){
		String url="http://c.m.163.com/nc/video/list/V9LG4B3A0/y/"+fromIndex+"-"+(fromIndex+20)+".html";
		return url;
	}
	
	public static String getTalkUrl(){
		String url="http://v.juhe.cn/joke/randJoke.php?key=038af288ef6adda3c5c52193161e918d";
		return url;
	}
	
	public static String getLuckUrl(String name){
		try {
			name=URLEncoder.encode(name,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url="http://web.juhe.cn:8080/constellation/getAll?consName="+name+"&type=today&key=517a0113e3ec1629c2aa7eb231a617cf";
		return url;
	}
}

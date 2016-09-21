package com.news.news.untils;

//笑话封装
public class Joke {  
	private String content; //内容
	private String updatetime;  //时间
	private String url; //图片链接
	public Joke(String content, String updatetime, String url) {
		super();
		this.content = content;
		this.updatetime = updatetime;
		this.url = url;
	}
	public Joke() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Joke [content=" + content + ", updatetime=" + updatetime + ", url=" + url + "]";
	}
	
}

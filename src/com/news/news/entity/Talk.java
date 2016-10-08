package com.news.news.entity;

public class Talk {
	private String content;

	private String hashId;

	private String unixtime;

	public Talk() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Talk(String content, String hashId, String unixtime) {
		super();
		this.content = content;
		this.hashId = hashId;
		this.unixtime = unixtime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHashId() {
		return hashId;
	}

	public void setHashId(String hashId) {
		this.hashId = hashId;
	}

	@Override
	public String toString() {
		return "Talk [content=" + content + ", hashId=" + hashId + ", unixtime=" + unixtime + "]";
	}

	public String getUnixtime() {
		return unixtime;
	}

	public void setUnixtime(String unixtime) {
		this.unixtime = unixtime;
	}
	
}

package com.news.news.entity.news;

import java.util.List;

public class New {
	private String stat;
	private List<Toutiao>data;
	public New(String stat, List<Toutiao> data) {
		super();
		this.stat = stat;
		this.data = data;
	}
	public New() {
		super();
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public List<Toutiao> getData() {
		return data;
	}
	public void setData(List<Toutiao> data) {
		this.data = data;
	}
	
	
}

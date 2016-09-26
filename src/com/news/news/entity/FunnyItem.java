package com.news.news.entity;

import java.io.Serializable;

/**
 * 用于描述 每一个具体的 Funny
 * 
 * @author Administrator
 * 
 */
public class FunnyItem implements Serializable{
	/**
	 * 
	 */
	private long unixtime;
	/**
	 * 标题
	 */
	private String content;
	/**
	 * 
	 */
	private String hashId;
	/**
	 * 更新的时间
	 */
	private String updatetime;
	/**
	 * 图片的URL
	 */
	private String url;

	public FunnyItem() {
		super();
	}

	public FunnyItem(long unixtime, String content, String hashId,
			String updatetime, String url) {
		super();
		this.unixtime = unixtime;
		this.content = content;
		this.hashId = hashId;
		this.updatetime = updatetime;
		this.url = url;
	}

	public long getUnixtime() {
		return unixtime;
	}

	public void setUnixtime(long unixtime) {
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
		return "FunnyItem [unixtime=" + unixtime + ", content=" + content
				+ ", hashId=" + hashId + ", updatetime=" + updatetime
				+ ", url=" + url + "]";
	}

}

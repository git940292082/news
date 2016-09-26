package com.news.news.model;



/**
 * 回调方法 
 * @author Shark Z 
 *
 */
public interface CommonCallback {
	
	
	
	/**
	 * 加载数据完成之后 自动执行的回调方法 
	 * @param obj
	 */
	void onDataLoaded(Object obj);

}

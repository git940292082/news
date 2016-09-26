package com.news.news.view;

import java.util.List;

import com.news.news.entity.Funny;
import com.news.news.entity.FunnyItem;





/**
 *FunnyFragment View 层 
 * @author Shark Z 
 *
 */
public interface FunnyView extends NewsView{
	
	
	
	/**
	 * 显示FunnyFragment list集合的方法
	 */
	public void showFunnyTextList(List<FunnyItem> funnys);

}

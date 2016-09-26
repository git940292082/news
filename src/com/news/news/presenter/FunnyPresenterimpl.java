package com.news.news.presenter;

import java.util.List;

import com.news.news.entity.Funny;
import com.news.news.entity.FunnyItem;
import com.news.news.model.CommonCallback;
import com.news.news.model.impl.FunnyModelimpl;
import com.news.news.view.FunnyView;





/**
 * FunnyFragment presenter 层 接口 实现类
 * @author Shark Z  
 *
 */
public class FunnyPresenterimpl implements FunnyPresenter{

	private FunnyView view;
	private FunnyModelimpl model;
	
	/**
	 * 构造方法  把数据和控件 组合起来
	 */
	public FunnyPresenterimpl(FunnyView view){
		this.view=view;
		model = new FunnyModelimpl();
		
		
	}
	
	
	@Override
	public void getAllFunnys() {
		model.getAllFunnys(new CommonCallback() {
			
			@Override
			public void onDataLoaded(Object obj) {
				//1.数据加载完毕自动调用回调方法 
				@SuppressWarnings("unchecked")
				List<FunnyItem> funnys=(List<FunnyItem>) obj;
				
				//2.显示界面
				view.showFunnyTextList(funnys);
				
				
			}
		});
		
		
		
		
		
	}

}

package com.news.news.presenter;



import com.news.news.entity.Luck;
import com.news.news.entity.Talk;
import com.news.news.model.FindModel;
import com.news.news.model.ILoadDataCallback;
import com.news.news.view.IFindView;

public class FindTalkPresenter {
	FindModel findModel;
	IFindView findView;
	public FindTalkPresenter(IFindView iFindView) {
		super();
		
		// TODO Auto-generated constructor stub
		findModel=new FindModel();
		findView=iFindView;
	}
	public void loadTalk(){
		findModel.loadTalk(new ILoadDataCallback() {
			@Override
			public void ok(Object obj) {
				// TODO Auto-generated method stub
				Talk talk=(Talk) obj;
				findView.showTalk(talk);
			}
			@Override
			public void err(String msg) {
				// TODO Auto-generated method stub
			}
		});
	}
	public void loadLuck(String name){
		findModel.loadLuck(name, new ILoadDataCallback() {
			
			@Override
			public void ok(Object obj) {
				// TODO Auto-generated method stub
				Luck luck=(Luck) obj;
				findView.showLuck(luck);
			}
			
			@Override
			public void err(String msg) {
				// TODO Auto-generated method stub
				
			}
		}, Luck.class);
	}
}

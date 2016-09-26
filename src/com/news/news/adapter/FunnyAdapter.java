package com.news.news.adapter;

import java.util.List;

import org.xutils.x;

import com.news.news.R;
import com.news.news.entity.FunnyItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * FunnyFragment 界面 adapter
 * @author Shark Z  
 *
 */
public class FunnyAdapter extends BaseAdapter{
	
	private List<FunnyItem> funnys;
	@SuppressWarnings("unused")
	private ListView listView;//移除某一线的时候 需要 listview 标示
	private LayoutInflater inflater;
	
	
	/**
	 * 构造方法
	 * @param context
	 * @param funnys
	 * @param listView
	 */
	public FunnyAdapter(Context context,List<FunnyItem> funnys,ListView listView){
		
		this.funnys=funnys;
		this.listView=listView;
		inflater=LayoutInflater.from(context);
		
		
	}
	

	@Override
	public int getCount() {
		return funnys.size();
	}
	@Override
	public FunnyItem getItem(int position) { 
		return funnys.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	//转换 View  
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder=null;
		
		if(convertView==null){
			
			//初始化控件
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_funny_fragment_list, null);
			
			holder.tvTitle=(TextView) convertView.findViewById(R.id.tv_title_item_funny);
			holder.ivFunnyPic=(ImageView) convertView.findViewById(R.id.iv_funny_pic_item_funny);
			
			convertView.setTag(holder);
			
		}
		
		//赋值
		FunnyItem funny = funnys.get(position);
		 
		holder=(ViewHolder) convertView.getTag();
		//--标题
		holder.tvTitle.setText(funny.getContent());
		
		
		/**
		 * 这里加载图片控件  也可以换成 webView 控件 直接拿到 图片 URL  显示 图片 
		 */
		//--图片
		x.image().bind(holder.ivFunnyPic, funny.getUrl());
		
		
		
		//返回
		return convertView;
	}
	
	/**
	 * Adapter优化  辅助类
	 * @author Administrator
	 *
	 */
	class ViewHolder {
		TextView tvTitle;
		ImageView ivFunnyPic;
	}
	

}

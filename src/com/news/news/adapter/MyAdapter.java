package com.news.news.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyAdapter<T> extends BaseAdapter {
	private Context context;
	private List<T> data;
	private LayoutInflater inflater;
	public MyAdapter(Context context, List<T> data) {
		super();
		setContext(context);
		setData(data);
		inflater=LayoutInflater.from(context);
	}
//<<<<<<< HEAD
//=======
//	//DSFGSGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
//	//DSFGSGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
//	//DSFGSGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
//	//DSFGSGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
//>>>>>>> branch 'master' of https://github.com/git940292082/json
	protected LayoutInflater getInflater() {
		return inflater;
	}
///////////////////////////////
	protected Context getContext() {
		return context;
	}
	protected List<T> getData() {
		return data;
	}

	private void setContext(Context context) {
		if(context==null){
			throw new IllegalArgumentException("参数contxt不允许为空");
		}
		this.context = context;
	}


	
	private void setData(List<T> data) {
		if(data==null){
			data=new ArrayList<T>();
		}
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View layout, ViewGroup parent) {
		// TODO Auto-generated method stub
		return layout;
	}

}

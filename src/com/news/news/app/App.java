package com.news.news.app;


import org.xutils.x;

import com.news.news.entity.User;

import android.app.Application;
import android.content.Context;
public class App  extends Application{
	public static Context context;
    public static User user=new User();
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		context =getApplicationContext();
		x.Ext.init(this);
		super.onCreate();
	}
	
}

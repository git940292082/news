package com.news.news.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.news.news.R;
import com.news.news.R.string;
import com.news.news.activity.MainActivity;
import com.news.news.entity.User;
import com.news.news.server.PreferencesService;
import com.news.news.server.Server;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

public class App  extends Application{
	public static Context context;
	public static User app_user=new User();
	private static RequestQueue mQueue;
	public static List<Object> collections;
	private static File file;
	public static String QQID;
	public static String QQKey;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		context =getApplicationContext();
		collections=new ArrayList<Object>();
		file=new File(Environment.getExternalStorageDirectory()+"/shouchang");
		x.Ext.init(this);
		loadCollection();
		startService(new Intent(this,Server.class));
		QQID=getResources().getString(R.string.qq_id);
		QQKey=getResources().getString(R.string.qq_key);
		super.onCreate();

		/**
		 * Shark Z 
		 * 1.在 项目 运行的时候 创建 Volley 请求队列 
		 * 2.获取 RequestQueue 对象
		 */
		this.mQueue=Volley.newRequestQueue(getApplicationContext()); 
		getUser();
	}

	/**
	 * 获取到偏好设置里面的用户信息**/
	private void getUser() {
		PreferencesService perfer = new PreferencesService(context);
		app_user=perfer.getUserPerferences();
		if(app_user==null){
			app_user=new User();
		}
	}
	private void loadCollection() {
		// TODO Auto-generated method stub
		try {
			ObjectInput input=new ObjectInputStream(new FileInputStream(file));
			collections  = (List<Object>) input.readObject();
			input.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取上下文 队列
	 * @return mQueue
	 */
	public static RequestQueue getmQueue(){
		return mQueue;
	}

	public static void setApp_User(User user){
		app_user.setName(user.getName());
		app_user.setPass(user.getPass());
		app_user.setEmail(user.getEmail());
		app_user.setIcon(user.getIcon());
	}
	public static boolean collection(Object object){
		collections.add(0, object);
		savecpllection();
		return savecpllection();
	}

	private static boolean savecpllection() {
		// TODO Auto-generated method stub
		try {
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(collections);
			out.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void clear(){
		collections.clear();
		savecpllection();

	}
}

package com.news.news.server;

import com.news.news.entity.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesService {
	private Context context;
	public PreferencesService(Context context) {  
		this.context = context;  
	}  

	public void saveUser(User user) {
		SharedPreferences preferences = context.getSharedPreferences("denglu", Context.MODE_PRIVATE);  
		Editor editor = preferences.edit();  
		editor.putString("name", user.getName());  
		editor.putString("pwd", user.getPass());  
		editor.putString("email",user.getEmail());
		editor.putString("icon",user.getIcon());
		editor.commit();  
	}  
	public User getUserPerferences() {
		User user=new User();
		SharedPreferences preferences = context.getSharedPreferences("denglu", Context.MODE_PRIVATE);  
		user.setName(preferences.getString("name", ""));  
		user.setPass(preferences.getString("pwd", ""));  
		user.setEmail(preferences.getString("email", ""));
		user.setIcon(preferences.getString("icon", ""));
		return user;  
	}
}

package com.news.news.server;

import java.util.List;

import com.news.news.app.App;
import com.news.news.database.RegLoginDao;
import com.news.news.entity.User;

import android.content.Context;
import android.util.Log;

public class RegLogin_Server {
	private List<User> users;
	private RegLoginDao dbDao;
	private PreferencesService ser;
	public RegLogin_Server(Context context){
		dbDao=new RegLoginDao(context);
		users=dbDao.getUsers(null,null);
		ser=new PreferencesService(context);
	}
	public String register(String name,String pwd,String email,String icon){
		for (User use:users){
			if (use.getName().equals(name)){
				return "该账号已存在！";
			}
			if (use.getEmail().equals(email)){
				return "该邮箱已被注册！";
			}
		}
		User user=new User();
		user.setName(name);
		user.setPass(pwd);
		user.setEmail(email);
		user.setIcon(icon);
		try {
			dbDao.insert(user);
			userSave(user);
		}catch (Exception e){
			e.printStackTrace();
		}
		return "注册成功";
	}
	public String login(String name,String pwd){
		for (User use:users){
			if ((use.getName().equals(name))&&(use.getPass().equals(pwd))){
				userSave(use);
				return "登录成功！";
			}
			if ((use.getEmail().equals(name))&&(use.getPass().equals(pwd))){
				userSave(use);
				return "登录成功！";
			}
		}
		for (User use:users){
			if(use.getName().equals(name)){
				return "密码错误，请重试";
			}
			if(use.getEmail().equals(name)){
				return "密码错误，请重试";
			}
		}
		return "没有该账号，请注册";
	}

	public long forGetPwd(String name, String email) {
		for (User use:users){
			if ((use.getName().equals(name))&&(use.getEmail().equals(email))){
				return use.getId();
			}
		}
		return -1;
	}
	public void userSave(User user){
		App.setApp_User(user);
		ser.saveUser(user);
	}
}

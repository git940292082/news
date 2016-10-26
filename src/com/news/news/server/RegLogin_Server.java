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
				return "���˺��Ѵ��ڣ�";
			}
			if (use.getEmail().equals(email)){
				return "�������ѱ�ע�ᣡ";
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
		return "ע��ɹ�";
	}
	public String login(String name,String pwd){
		for (User use:users){
			if ((use.getName().equals(name))&&(use.getPass().equals(pwd))){
				userSave(use);
				return "��¼�ɹ���";
			}
			if ((use.getEmail().equals(name))&&(use.getPass().equals(pwd))){
				userSave(use);
				return "��¼�ɹ���";
			}
		}
		for (User use:users){
			if(use.getName().equals(name)){
				return "�������������";
			}
			if(use.getEmail().equals(name)){
				return "�������������";
			}
		}
		return "û�и��˺ţ���ע��";
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

package com.news.news.untils;

public class Luck {
	private String date; //时间
	private String name; //星座名字
	private String datetime;	//时间
	private String all;  //综合运势     %
	private String color; //幸运颜色
	private String health; //健康状况 %
	private String love;   //恋爱指数  %
	private String number;  //幸运数字
	private String QFriend;	//配对星座
	private String summary;	//总结
	public Luck() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Luck(String date, String name, String datetime, String all, String color, String health, String love,
			String number, String qFriend, String summary) {
		super();
		this.date = date;
		this.name = name;
		this.datetime = datetime;
		this.all = all;
		this.color = color;
		this.health = health;
		this.love = love;
		this.number = number;
		QFriend = qFriend;
		this.summary = summary;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getAll() {
		return all;
	}
	public void setAll(String all) {
		this.all = all;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getLove() {
		return love;
	}
	public void setLove(String love) {
		this.love = love;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getQFriend() {
		return QFriend;
	}
	public void setQFriend(String qFriend) {
		QFriend = qFriend;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Override
	public String toString() {
		return "Luck [date=" + date + ", name=" + name + ", datetime=" + datetime + ", all=" + all + ", color=" + color
				+ ", health=" + health + ", love=" + love + ", number=" + number + ", QFriend=" + QFriend
				+ ", summary=" + summary + "]";
	}
	
	
}

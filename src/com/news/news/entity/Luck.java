package com.news.news.entity;

public class Luck {
	
	private int date; //时间

	private String name;//星座名字

	private String datetime;//时间

	private String all;//综合运势     %

	private String color;//幸运颜色

	private String health;//健康状况 %

	private String love;//恋爱指数  %

	private String money;

	private int number;//幸运数字

	private String QFriend;	//配对星座

	private String summary;	//总结

	private String work;

	private String resultcode;

	private int error_code;

	public Luck() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Luck(int date, String name, String datetime, String all, String color, String health, String love,
			String money, int number, String qFriend, String summary, String work, String resultcode, int error_code) {
		super();
		this.date = date;
		this.name = name;
		this.datetime = datetime;
		this.all = all;
		this.color = color;
		this.health = health;
		this.love = love;
		this.money = money;
		this.number = number;
		QFriend = qFriend;
		this.summary = summary;
		this.work = work;
		this.resultcode = resultcode;
		this.error_code = error_code;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
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

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	@Override
	public String toString() {
		return "Luck [date=" + date + ", name=" + name + ", datetime=" + datetime + ", all=" + all + ", color=" + color
				+ ", health=" + health + ", love=" + love + ", money=" + money + ", number=" + number + ", QFriend="
				+ QFriend + ", summary=" + summary + ", work=" + work + ", resultcode=" + resultcode + ", error_code="
				+ error_code + "]";
	}
	
	
}

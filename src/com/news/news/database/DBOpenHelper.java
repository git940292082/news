package com.news.news.database;

		import android.content.Context;
		import android.database.sqlite.SQLiteDatabase;
		import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "the_user.db", null, 1);
	}
  
	@Override
	public void onCreate(SQLiteDatabase db) {
		// 瑜版挸鍨卞鐑樻殶閹诡喖绨遍弮璁圭礉閸掓稑缂撻崙鐑樻殶閹诡喛銆�		
		String sql = "CREATE TABLE users ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+"_user VARCHAR(16) NOT NULL,"
				+ "name VARCHAR(16) NOT NULL, "
				+ "tel CHAR(11) "
				+ ")";
		db.execSQL(sql);
		String sqlo = "CREATE TABLE login ("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "_user VARCHAR(16) UNIQUE NOT NULL, "
				+"_email VARCHAR(16) NOT NULL,"
				+"_pwd VARCHAR(16) NOT NULL"
				+ ")";
		db.execSQL(sqlo);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 瑜版挻鏆熼幑顔肩氨閻楀牊婀伴崡鍥╅獓閺冨灈锟斤腹锟斤拷
	}

}

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
		// 褰撳垱寤烘暟鎹簱鏃讹紝鍒涘缓鍑烘暟鎹〃
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
		// 褰撴暟鎹簱鐗堟湰鍗囩骇鏃垛�︹��
	}

}

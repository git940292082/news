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
		// 鐟滅増鎸搁崹鍗烆嚈閻戞ɑ娈堕柟璇″枛缁ㄩ亶寮拋鍦闁告帗绋戠紓鎾诲礄閻戞ɑ娈堕柟璇″枦閵嗭拷		
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
		// 鐟滅増鎸婚弳鐔煎箲椤旇偐姘ㄩ柣妤�墛濠�即宕￠崶鈺呯崜闁哄啫鐏堥敓鏂よ吂閿熸枻鎷�	}
	}
}

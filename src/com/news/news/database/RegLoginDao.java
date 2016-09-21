package com.news.news.database;

import java.util.ArrayList;
import java.util.List;

import com.news.news.entity.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
	
/**
 * Created by admin on 2016/9/7.
 */
public class RegLoginDao {
        private Context context;

        public RegLoginDao(Context context) {
            super();
            this.context = context;
        }

        /**
         *  鏁版嵁搴撲慨鏀�
         *
         * @param User
         *
         * @return ??????????
         */
        public int update(User user) {
            // ????????
            int affectedRows = 0;
            // ???DBOpenHelper????
            DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
            // ???SQLiteDatabase????
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            // ???????
            ContentValues values = new ContentValues();
            values.put("_pwd", user.getPass());
            String whereClause = "_id=? and _user=?";
            String[] whereArgs = new String[2];
            whereArgs[0]=user.getId()+"";
            whereArgs[1]=user.getUser()+"";
            affectedRows = db.update("login", values, whereClause, whereArgs);
            // ??????
            db.close();
            // ????
            return affectedRows;
        }

        /**
         * 鏁版嵁搴撳垹闄�
         *
         * @param id
         *
         * @return ??????????
         */
        public int delete(long id) {
            // ????????
            int affectedRows = 0;
            // ???DBOpenHelper????
            DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
            // ???SQLiteDatabase????
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            // ?????????
            String whereClause = "_id=?";
            String[] whereArgs = { id + "" };
            affectedRows = db.delete("login", whereClause, whereArgs);
            // ??????
            db.close();
            // ????
            return affectedRows;
        }

        /**
         * 鑾峰彇鎵�鏈夌敤鎴锋暟鎹�
         *
         * @param whereClause
         *            ???????????where???
         * @param whereArgs
         *            where????脨赂????????
         * @return ???????????List?????????????null
         */
        public List<User> getUsers(String whereClause, String[] whereArgs) {
            // ????????
            List<User> users = new ArrayList<User>();
            // ???DBOpenHelper????
            DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
            // ???SQLiteDatabase????
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            // ??????
            // -- ???
            Cursor c = db.query("login", null, whereClause, whereArgs, null, null, "_id DESC");
            // -- ????Cursor
            if (c.moveToFirst()) {
                for (; !c.isAfterLast(); c.moveToNext()) {
                    User user = new User();
                    user.setId(c.getLong(0));
                    user.setUser(c.getString(1));
                    user.setEmail(c.getString(2));
                    user.setPass(c.getString(3));
                    users.add(user);
                }
            }
            // ??????
            c.close();
            // ????
            return users;
        }

        /**
         * 鏁版嵁搴撴煡璇�
         *
         * @return Cursor????
         */
        @Deprecated
        public Cursor query() {
            // ????????
            Cursor c = null;
            // ???DBOpenHelper????
            DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
            // ???SQLiteDatabase????
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            // ????????
            c = db.query("login", null, null, null, null, null, "_id DESC");
            // ?????? -- ??????????????
            // ????
            return c;
        }

        /**
         *鏁版嵁搴撳鍔�
         *
         * @param User
         *            ????????
         * @return ???????????ID????????????????-1
         */
        public long insert(User user) {
            // ????????
            long id = -1;
            // ???DBOpenHelper????
            DBOpenHelper dbOpenHelper = new DBOpenHelper(context);
            // ???SQLiteDatabase????
            SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
            // ??????????
            String table = "login";
            ContentValues values = new ContentValues();
            values.put("_user",user.getUser());
            values.put("_email", user.getEmail());
            values.put("_pwd", user.getPass());
            id = db.insert(table, null, values);
            // ??????
            db.close();
            // ????
            return id;
        }


}

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
         *  閺佺増宓佹惔鎾叉叏閺�锟�
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
            whereArgs[1]=user.getName()+"";
            affectedRows = db.update("login", values, whereClause, whereArgs);
            // ??????
            db.close();
            // ????
            return affectedRows;
        }

        /**
         * 閺佺増宓佹惔鎾冲灩闂勶拷
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
         * 閼惧嘲褰囬幍锟介張澶屾暏閹撮攱鏆熼幑锟�
         *
         * @param whereClause
         *            ???????????where???
         * @param whereArgs
         *            where????鑴ㄨ祩????????
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
                    user.setName(c.getString(1));
                    user.setEmail(c.getString(2));
                    user.setPass(c.getString(3));
                    user.setIcon(c.getString(4));
                    users.add(user);
                }
            }
            // ??????
            c.close();
            // ????
            return users;
        }

        /**
         * 閺佺増宓佹惔鎾寸叀鐠囷拷
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
         *閺佺増宓佹惔鎾愁杻閸旓拷
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
            values.put("_user",user.getName());
            values.put("_email", user.getEmail());
            values.put("_pwd", user.getPass());
            values.put("_icon", user.getIcon());
            id = db.insert(table, null, values);
            // ??????
            db.close();
            // ????
            return id;
        }


}

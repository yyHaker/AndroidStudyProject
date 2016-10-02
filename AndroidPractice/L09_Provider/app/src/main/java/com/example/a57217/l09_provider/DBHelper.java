package com.example.a57217.l09_provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库的帮助类
 * Created by 57217 on 2016/8/27.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "yyHack.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("TAG","....onCreate()......");
       //建表
        sqLiteDatabase.execSQL("create table person(_id integer primary key autoincrement,name varchar)");
        //插入数据
        sqLiteDatabase.execSQL("insert into person (name) values ('tom')");
        sqLiteDatabase.execSQL("insert into person (name) values ('lily')");
        sqLiteDatabase.execSQL("insert into person (name) values ('yy')");
        sqLiteDatabase.execSQL("insert into person (name) values ('xx')");
        sqLiteDatabase.execSQL("insert into person (name) values ('july')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

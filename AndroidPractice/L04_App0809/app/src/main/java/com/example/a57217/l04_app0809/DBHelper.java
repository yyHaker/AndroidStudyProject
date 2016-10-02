package com.example.a57217.l04_app0809;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @Description 数据库操作的帮助类
 * @Author yyHaker
 * @Time ${Date}
 */
public class DBHelper  extends SQLiteOpenHelper{

    public DBHelper(Context context) {
        super(context, "contacts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("TAG","Create table contacts in onCreate");
        //创建表
        db.execSQL("create table black_contacts(_id integer primary key autoincrement,name varchar)");
        db.execSQL("insert into black_contacts(name) values('123')");
        db.execSQL("insert into black_contacts(name) values('456')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

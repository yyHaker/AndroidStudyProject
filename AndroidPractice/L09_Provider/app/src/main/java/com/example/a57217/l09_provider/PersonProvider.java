package com.example.a57217.l09_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * person内容信息提供类
 * Created by 57217 on 2016/8/27.
 */
public class PersonProvider extends ContentProvider {
    //用来存放所有合法的uri的容器
    private  static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
    //保存一些合法的uri
       //content://com.example.a57217.l09_provider.PersonProvider/person  不按照id查询
       //content://com.example.a57217.l09_provider.PersonProvider/person/3 按照id查询
     static {
        matcher.addURI("com.example.a57217.l09_provider.PersonProvider","/person",1);
        matcher.addURI("com.example.a57217.l09_provider.PersonProvider","/person/#",2);//#匹配任意数字
    }

    private  DBHelper dbHelper;

    @Override
    public boolean onCreate() {
    dbHelper=new DBHelper(getContext());

        return false;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
           //得到连接对象
        SQLiteDatabase database=dbHelper.getReadableDatabase();

         //1.匹配uri，返回code,如果合法进行查询，如果不合法，抛出异常
         int code =matcher.match(uri);
         if (code==1){
             //不根据id查询
             Cursor cursor=database.query("person",projection,selection,selectionArgs,null,null,null);
             return cursor;
         }else if (code==2){
             //根据id 查询
             //得到id
              long id= ContentUris.parseId(uri);
             //查询
             Cursor cursor=database.query("person",projection,"_id=?",new String[]{id+""},null,null,null);
            return cursor;
         }else{
             throw  new RuntimeException("查询的uri不合法");
         }
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        //得到连接对
        SQLiteDatabase database=dbHelper.getReadableDatabase();
        //匹配uri，返回code；如果合法进行插入，不合法则抛出异常
        int code=matcher.match(uri);
        if (code==1){
            long id=database.insert("person",null,contentValues);
              //将id添加到uri中
              uri=ContentUris.withAppendedId(uri,id);
            database.close();
            return uri;
        }else{
            database.close();
            throw new RuntimeException("插入的uri不合法");
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.e("TAG","PersonProvider delete()");
        //得到连接对象
        SQLiteDatabase database =dbHelper.getReadableDatabase();
        //匹配uri，返回code ；如果合法进行删除，如果不合法，抛出异常
         int code=matcher.match(uri);
        int deleteCount=-1;
        if (code==1){
           deleteCount= database.delete("person",selection,selectionArgs);

        }else if(code==2){
             long id=ContentUris.parseId(uri);
            deleteCount=database.delete("person","_id="+id,null);

        }else {
            database.close();
            throw new RuntimeException("删除的uri不合法");
        }
        database.close();
        return deleteCount;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        //得到连接对象
        SQLiteDatabase database =dbHelper.getReadableDatabase();
        //匹配uri，返回code ；如果合法进行更新，如果不合法，抛出异常
        int code=matcher.match(uri);
        int updateCount=-1;
        if (code==1){
            updateCount= database.update("person",contentValues,selection,selectionArgs);

        }else if(code==2){
            long id=ContentUris.parseId(uri);
           updateCount=database.update("person",contentValues,"_id="+id,null);

        }else {
            database.close();
            throw new RuntimeException("删除的uri不合法");
        }
        database.close();
        return updateCount;
    }
}

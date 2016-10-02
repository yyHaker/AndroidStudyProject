package com.example.a57217.l04_app0809;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 操作black_number表的Dao类
 * @Author yyHaker
 * @Time 2016/8/9
 */
public class BlackContactDao {

     private DBHelper dbHelper;

       public BlackContactDao(Context context){
           dbHelper=new DBHelper(context);
       }

    /**
     * 添加一条记录
     * @param blackContact
     */
    public void addBlackContacts(BlackContact blackContact){
       //1.得到连接
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        //2.执行insert into black_contacts(number) values(xxx)
        ContentValues values=new ContentValues();
        values.put("name",blackContact.getNumber());
         Long insertID= sqLiteDatabase.insert("black_contacts",null,values);
        Log.i("TAG","insertID="+insertID);

           //2.2设置id
           blackContact.setId(Integer.parseInt(insertID.toString()));
        //3.关闭
        sqLiteDatabase.close();
    }

    /**
     * 根据id删除一条记录
     * @param id
     */
    public void deleteBlackContactsById(int id){
        //1.得到连接
         SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        //2.执行delete from "black_contacts" where _id=?
         int deleteCount=sqLiteDatabase.delete("black_contacts","_id=?",new String[]{id+""});
        Log.i("TAG","deleteCount="+deleteCount);
        //3.关闭
        sqLiteDatabase.close();
    }

    /**
     * 更新一条记录
     * @param blackContact
     */
    public void updateBlackContacts(BlackContact blackContact){
        //1.得到连接
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        //2.执行update black_contacts set name=? where _id=?
         ContentValues values=new ContentValues();
        values.put("name",blackContact.getNumber());
       int updateCount= sqLiteDatabase.update("black_contacts",values,"_id=?",new String[]{blackContact.getId()+""});
        Log.i("TAG","updateCount="+updateCount);
        //3.关闭
        sqLiteDatabase.close();
    }

    /**
     * 查询所有记录封装成List
     * @return
     */

    public List<BlackContact> getAllBlackContacts(){
        //1.得到连接
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        //2.执行select * from black_contacts
          Cursor cursor= sqLiteDatabase.query("black_contacts",null,null,null,null,null,"_id desc",null);
            List<BlackContact> blackContactList=new ArrayList<BlackContact>();
          while(cursor.moveToNext()){
              BlackContact blackContact=new BlackContact(cursor.getInt(0),cursor.getString(1));
              blackContactList.add(blackContact);
          }
        //3.关闭
         cursor.close();
        sqLiteDatabase.close();
        return blackContactList;
    }
}

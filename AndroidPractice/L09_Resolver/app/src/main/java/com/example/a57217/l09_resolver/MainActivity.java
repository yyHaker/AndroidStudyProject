package com.example.a57217.l09_resolver;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  insert(View view){
   //得到contentResolver对象
        ContentResolver resolver=getContentResolver();
        //调用其insert
        Uri uri=Uri.parse("content://com.example.a57217.l09_provider.PersonProvider/person/");
        ContentValues values=new ContentValues();
        values.put("name","jack");
        uri= resolver.insert(uri,values);
        Toast.makeText(this,uri.toString(),Toast.LENGTH_LONG).show();
    }

    public void  delete(View view){
       //得到ContentResolver对象
        ContentResolver resolver=getContentResolver();
        Uri uri=Uri.parse("content://com.example.a57217.l09_provider.PersonProvider/person/6");
        //执行delete
         int deleteCount=resolver.delete(uri,null,null);
        Toast.makeText(this,"deleteCount="+deleteCount,Toast.LENGTH_LONG).show();
    }

    public void  update(View view){
    //得到ContentResolver对象
        ContentResolver resolver=getContentResolver();
        //执行update
        Uri uri=Uri.parse("content://com.example.a57217.l09_provider.PersonProvider/person/4");
        ContentValues values=new ContentValues();
        values.put("name","Jack2");
        int updateCount=resolver.update(uri,values,null,null);
        Toast.makeText(this,"updateCount="+updateCount,Toast.LENGTH_LONG).show();
    }

    //通过ContentResolver调用ContentProvider查询记录
    public void  query(View view){
       //1.得到contentResolver 对象
        ContentResolver resolver=getContentResolver();
        //Uri uri=Uri.parse("content://com.example.a57217.l09_provider.PersonProvider/person/3");根据id查询记录
        Uri uri=Uri.parse("content://com.example.a57217.l09_provider.PersonProvider/person");//查询所有记录
        //2.调用其query，得到cursor
        Cursor cursor=resolver.query(uri,null,null,null,null);
        //3.取出cursor中的数据并显示
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String name =cursor.getString(1);
            Toast.makeText(this,id+":"+name,Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
}

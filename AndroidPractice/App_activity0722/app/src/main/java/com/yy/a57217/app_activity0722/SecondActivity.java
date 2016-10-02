package com.yy.a57217.app_activity0722;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 第二个Activity:SecondActivity
 */
public class SecondActivity extends AppCompatActivity {
   private EditText edit_second_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edit_second_message= (EditText) findViewById(R.id.edit_second_message);
        //3）得到Intent对象
         Intent intent=getIntent();
        //4）通过Intent获取额外数据
        String message=intent.getStringExtra("message");
        //5）显示EditText

        edit_second_message.setText(message);
    }


    public void  back1 (View view){
        //关闭当前页面
        finish();
    }
    public void back2(View view){
        //准备一个带额外数据的intent对象
        Intent data=new Intent();
        String result=edit_second_message.getText().toString();
        data.putExtra("result",result);

        int reslutCode=3;
        //保存数据
        setResult(reslutCode,data);

        //关闭当前页面
        finish();
    }
}

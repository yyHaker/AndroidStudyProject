package com.yy.a57217.app_activity0722;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 主界面MainActivity
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //定义成员变量
    private EditText edit_main_message;
    private Button btn_main_start1;
    private Button btn_main_start2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图对象
        edit_main_message=(EditText) findViewById(R.id.edit_main_message);
        btn_main_start1=(Button) findViewById(R.id.btn_main_start1);
        btn_main_start2=(Button) findViewById(R.id.btn_main_start2);
        //设置点击监听
         btn_main_start1.setOnClickListener(this);
        btn_main_start2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {//view就是发生事件的视图对象（操作的）
        if(view==btn_main_start1){
            //Toast.makeText(this,"一般启动",Toast.LENGTH_SHORT).show();
            //1)创建Intent对象(显示)
            Intent secondIntent=new Intent(this,SecondActivity.class);
                  //1.1)通过Intent携带额外数据(从EditText输入的数据)
                String message=edit_main_message.getText().toString();
                secondIntent.putExtra("message",message);
            //2）启动Activity
            startActivity(secondIntent);
        }else if (view==btn_main_start2){
            //Toast.makeText(this,"带回调启动",Toast.LENGTH_LONG).show();
            //1)创建Intent对象(显示)
            Intent secondIntent=new Intent(this,SecondActivity.class);
            //1.1)通过Intent携带额外数据(从EditText输入的数据)
            String message=edit_main_message.getText().toString();
            secondIntent.putExtra("message",message);
              // 2)带回调启动Activity
            int requestcode=2;
            startActivityForResult(secondIntent,requestcode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        //判断code
         if (requestCode==2&&resultCode==3){
             //从data中取数据
             String result=data.getStringExtra("result");
             //显示到editText
             edit_main_message.setText(result);
         }
    }
}

package com.yy.a57217.app_0722_activitypractice;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

       private EditText edit_main_message;
       private EditText edit_main_telephoneNumber;
       private Button btn_main_call;
       private Button btn_main_send;

    private View.OnClickListener onClickListener=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if(view==btn_main_call){
                //Toast.makeText(MainActivity.this,"点击打电话",Toast.LENGTH_SHORT).show();
                //1.创建一个Intent(隐式)
                Intent intent=new Intent(Intent.ACTION_DIAL);
                //2.携带数据
                String number=edit_main_telephoneNumber.getText().toString();
                intent.setData(Uri.parse("tel:"+number));
                //3.startActivity
                startActivity(intent);
            }else if(view==btn_main_send){
                //Toast.makeText(MainActivity.this,"点击发短信",Toast.LENGTH_SHORT).show();
               Intent intent=new Intent(Intent.ACTION_SENDTO);
                String number=edit_main_telephoneNumber.getText().toString();
                String message=edit_main_message.getText().toString();
                intent.setData(Uri.parse("smsto:"+number));
                 //携带额外数据
                intent.putExtra("sms_body",message);
                startActivity(intent);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图对象
        edit_main_message=(EditText) findViewById(R.id.edit_main_message);
        edit_main_telephoneNumber=(EditText)findViewById(R.id.edit_main_telephoneNumber);
        btn_main_call=(Button)findViewById(R.id.btn_main_call);
        btn_main_send=(Button)findViewById(R.id.btn_main_send);
        //给视图对象设置点击监听
        btn_main_call.setOnClickListener(onClickListener);
        btn_main_send.setOnClickListener(onClickListener);

        //给视图对象设置长按监听
        btn_main_call.setOnLongClickListener(this);
        btn_main_send.setOnLongClickListener(this);
    }

    @Override
    public boolean onLongClick(View view) {
       if(view==btn_main_call){
          //Toast.makeText(MainActivity.this,"长按打电话",Toast.LENGTH_SHORT).show();
           //1.创建一个隐式意图
           Intent intent=new Intent(Intent.ACTION_CALL);
           // 2.携带数据
           String number=edit_main_telephoneNumber.getText().toString();
           intent.setData(Uri.parse("tel:"+number));
           //3.startActivity
           startActivity(intent);
       }else if(view==btn_main_send){
          //Toast.makeText(MainActivity.this,"长按发短信",Toast.LENGTH_SHORT).show();
            //1.得到SmsManager对象
           SmsManager smsManager=SmsManager.getDefault();
            //2.发送文本信息(短信)
           String number=edit_main_telephoneNumber.getText().toString();
           String sms=edit_main_message.getText().toString();
           smsManager.sendTextMessage(number,null,sms,null,null);
       }
        //表示此事件已经被消费了，不会再触发点击
        return true;


    }
}

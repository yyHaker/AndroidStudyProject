package com.example.a57217.l06keyevent;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private  Boolean exit=false;//标识是否可以退出
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if (msg.what==1){
               exit=false;
           }
        }
    };

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
         if (event.getKeyCode()==KeyEvent.KEYCODE_BACK){
              if(!exit){
                   exit=true;
                  Toast.makeText(this,"再按一次即可退出",Toast.LENGTH_SHORT).show();
                  handler.sendEmptyMessageDelayed(1,2000);
                  return  true;//不退出
              }
         }
        return super.onKeyUp(keyCode, event);
    }
}

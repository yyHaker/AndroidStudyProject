package com.yy.a57217.app0721;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     private Button btn_main_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局并设置相应的视图对象
        setContentView(R.layout.activity_main);
        /**
         * 实现点击按钮，开始下载的文本小提示，并更新按钮显示为下载中
         */
        //1.得到button对象
        btn_main_download=(Button) findViewById(R.id.btn_main_download);
        //2.给button设置点击监听
        btn_main_download.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {//在回调方法中
                //2.1实现开始下载的文本小提示
                    //如何得到外部类的对象：类名.this
//                Toast toast= Toast.makeText(MainActivity.this,"开始下载....",Toast.LENGTH_SHORT);
//                toast.show();
                Toast.makeText(MainActivity.this,"开始下载....",Toast.LENGTH_SHORT).show();
                //2.2更新button显示的文本
                btn_main_download.setText("正在下载中...");
            }
        });

    }

}

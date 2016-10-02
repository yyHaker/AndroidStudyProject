package com.example.a57217.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//我的第一个Android程序
public class MainActivity extends AppCompatActivity {
    /**
     * 重写的方法
     * onCreate:在当前(activity)对象创建的时候自动调用
     * 回调方法： 系统在一定条件下自动调用，基本都是以on开头
     *   这些方法我们不需要调用，一般都是去重写此类方法
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //调用父类处理方法进行初始化
        super.onCreate(savedInstanceState);
        //设置窗口要显示的内容视图（界面/布局）
        //指定布局文件在R所对应的变量，加载布局文件最终显示到窗口中
        setContentView(R.layout.activity_main);

    }
}

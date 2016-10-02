package com.yy.a57217.app03gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridView gv_main;
    private MainAdapter adapter;
    String []names=new String[]{"手机防盗","通讯卫视","软件管理","流量管理","进程管理","手机杀毒",
            "缓存清理","高级工具","设置中心"};
    int []icons=new int[]{R.drawable.widget01,R.drawable.widget02,R.drawable.widget03,R.drawable.widget04,R.drawable.widget05,
    R.drawable.widget06,R.drawable.widget07,R.drawable.widget08,R.drawable.widget09};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv_main=(GridView) findViewById(R.id.gv_main);
        adapter=new MainAdapter(this,names,icons);
        gv_main.setAdapter(adapter);

        //给gridItem设置点击监听
        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //得到当前点击的名称
                String name=names[i];
                //提示
                Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
            }
        });

    }
}

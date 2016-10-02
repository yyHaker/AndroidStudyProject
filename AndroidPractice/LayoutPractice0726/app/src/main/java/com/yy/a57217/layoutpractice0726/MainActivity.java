package com.yy.a57217.layoutpractice0726;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemLongClickListener {

    private ListView lv_main_app;
    private List<AppInfor> data;
    private AppAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化成员变量
        lv_main_app=(ListView) findViewById(R.id.lv_main_app);
        data=getAllAppInfos();
        adapter=new AppAdapter();
        lv_main_app.setAdapter(adapter);

        //给LIstView设置监听
        lv_main_app.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             *
             * @param adapterView  :ListView
             * @param view  :当前行的item视图对象
             * @param i    ：当前行的下标
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //提示当前行的应用名称
                String appName=data.get(i).getAppName();
                Toast.makeText(MainActivity.this,appName,Toast.LENGTH_SHORT).show();
            }
        });

        //给LIstView设置长按监听
         lv_main_app.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        //删除当前行
           //删除当前行的数据
           data.remove(i);
           //通知更新列表，使用所有缓存的item视图
           adapter.notifyDataSetChanged();


        return true;
    }


    class AppAdapter extends BaseAdapter{


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        //返回带数据当前行的item视图对象
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

              //1.如果view是null,加载item的布局文件
             if(view==null){
               view= View.inflate(MainActivity.this,R.layout.item_main,null);
              }
              //2.得到当前行的数据对象
             AppInfor appInfor=data.get(i);
              //3.得到当前行需要更新的子view对象
             ImageView imageView=(ImageView) view.findViewById(R.id.iv_item_icon);
             TextView textView=(TextView) view.findViewById(R.id.tv_item_name);
              //4.给视图设置数据
              imageView.setImageDrawable(appInfor.getIcon());
              textView.setText(appInfor.getAppName());

             return view;
        }
    }


    /*
	 * 得到手机中所有应用信息的列表
	 * AppInfo
	 *  Drawable icon  图片对象
	 *  String appName
	 *  String packageName
	 */
    protected List<AppInfor> getAllAppInfos() {

        List<AppInfor> list = new ArrayList<AppInfor>();
        // 得到应用的packgeManager
        PackageManager packageManager = getPackageManager();
        // 创建一个主界面的intent
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        // 得到包含应用信息的列表
        List<ResolveInfo> ResolveInfos = packageManager.queryIntentActivities(
                intent, 0);
        // 遍历
        for (ResolveInfo ri : ResolveInfos) {
            // 得到包名
            String packageName = ri.activityInfo.packageName;
            // 得到图标
            Drawable icon = ri.loadIcon(packageManager);
            // 得到应用名称
            String appName = ri.loadLabel(packageManager).toString();
            // 封装应用信息对象
            AppInfor appInfo = new AppInfor(icon, appName, packageName);
            // 添加到list
            list.add(appInfo);
        }
        return list;
    }

}

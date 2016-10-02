package com.yy.a57217.textview0726;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView lv_main;
    private List<ShopInfor> shopInforList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main=(ListView) findViewById(R.id.lv_main);

        /**
         * 方法一：使用ArrayAdapter配合ListView显示数据
         */
       /**
        //1.准备集合数据
        String []datas={"A","B","C","D","E","F","G","H","I","J","K","l"};
        //2.准备ArrayAdapter对象
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.item_array_adapter,datas);
        //3.设置Adapter显示列表
         lv_main.setAdapter(adapter);
       */
        /**
         * 方法二:使用SimpleAdapter配合ListView显示数据
         */
        /**
        //1.准备集合数据
        List<Map<String,Object>> data=new ArrayList<Map<String,Object>>();

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("item",R.drawable.f1);
        map.put("name","name--1");
        map.put("content","content--1");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("item",R.drawable.f2);
        map.put("name","name--2");
        map.put("content","content--2");
        data.add(map);

         map=new HashMap<String,Object>();
        map.put("item",R.drawable.f3);
        map.put("name","name--3");
        map.put("content","content--3");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("item",R.drawable.f4);
        map.put("name","name--4");
        map.put("content","content--4");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("item",R.drawable.f5);
        map.put("name","name--5");
        map.put("content","content--5");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("item",R.drawable.f6);
        map.put("name","name--6");
        map.put("content","content--6");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("item",R.drawable.f7);
        map.put("name","name--7");
        map.put("content","content--7");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("item",R.drawable.f8);
        map.put("name","name--8");
        map.put("content","content--8");
        data.add(map);

        map=new HashMap<String,Object>();
        map.put("item",R.drawable.f9);
        map.put("name","name--9");
        map.put("content","content--9");
        data.add(map);

        //map对象中key的数组，用于得到对应的value
        String []from={"item","name","content"};
        //Item布局文件中的字view的id的数组
        int []to={R.id.iv_item_icon,R.id.tv_item_name,R.id.tv_item_content};
        //2.准备ArrayAdapter对象
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.item_simple_adpter,from,to);
        //3.设置Adapter显示列表
        lv_main.setAdapter(adapter);
          */

        /**
         * 方法三使用BaseAdapter配合ListView显示数据
         */

        //1.准备集合数据
        shopInforList=new ArrayList<ShopInfor>();
        shopInforList.add(new ShopInfor(R.drawable.f1,"name--1","content--1"));
        shopInforList.add(new ShopInfor(R.drawable.f2,"name--2","content--2"));
        shopInforList.add(new ShopInfor(R.drawable.f3,"name--3","content--3"));
        shopInforList.add(new ShopInfor(R.drawable.f4,"name--4","content--4"));
        shopInforList.add(new ShopInfor(R.drawable.f5,"name--5","content--5"));
        shopInforList.add(new ShopInfor(R.drawable.f6,"name--6","content--6"));
        shopInforList.add(new ShopInfor(R.drawable.f7,"name--7","content--7"));
        shopInforList.add(new ShopInfor(R.drawable.f8,"name--8","content--8"));
        shopInforList.add(new ShopInfor(R.drawable.f9,"name--9","content--9"));
        shopInforList.add(new ShopInfor(R.drawable.f10,"name--10","content--10"));
        //2.准备BaseAdapter对象
        MyAdapter adapter=new MyAdapter();
        //设置Adapter显示数据
        lv_main.setAdapter(adapter);

    }

  class MyAdapter extends BaseAdapter{
      //返回数据集合的数量
      @Override
      public int getCount() {
          return shopInforList.size();
      }
      //返回指定下标对应的数据对象
      @Override
      public Object getItem(int i) {
          return shopInforList.get(i);
      }

      @Override
      public long getItemId(int i) {
          return 0;
      }
      //返回指定下标所对应的item的view对象
      //i:下标
      //view:
      //parent:ListView对象
      @Override
      public View getView(int i, View view, ViewGroup viewGroup) {
          //加载item布局，得到view对象
          if(view==null){
              view=View.inflate(MainActivity.this,R.layout.item_simple_adpter,null);
          }
        //根据下标设置相应的数据
             //得到当前行的数据对象
           ShopInfor shopInfor=shopInforList.get(i);
             //从所加载的view对象中得到子view对象
          ImageView imageView=(ImageView) view.findViewById(R.id.iv_item_icon);
          TextView textViewContent=(TextView) view.findViewById(R.id.tv_item_content);
          TextView textViewName=(TextView) view.findViewById(R.id.tv_item_name);
             //设置相应的数据
          imageView.setImageResource(shopInfor.getIcon());
          textViewContent.setText(shopInfor.getContent());
          textViewName.setText(shopInfor.getName());
          return view;
      }
  }


}

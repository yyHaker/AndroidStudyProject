package com.example.a57217.app09_provider;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactListActivity extends ListActivity  implements AdapterView.OnItemClickListener{

    private ListView listView;
    private  ContactAdapter adapter;
    private List<Map<String,String>> data=new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

         listView=getListView();
        adapter=new ContactAdapter();
        //查询得到联系人表的数据
        ContentResolver resolver =getContentResolver();
        String []projection ={Phone.DISPLAY_NAME, Phone.NUMBER};
        Cursor cursor=resolver.query(Phone.CONTENT_URI,projection,null,null,null);
         //取出cursor中的数据保存到data
         while (cursor.moveToNext()){
             String name=cursor.getString(0);
             String number=cursor.getString(1);
             Map<String,String> map=new HashMap<String,String>();
             map.put("name",name);
             map.put("number",number);

             data.add(map);
         }
        cursor.close();


        //显示列表
        listView.setAdapter(adapter);

    }

    class ContactAdapter extends BaseAdapter{
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

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
             if (convertView==null){
                 convertView=View.inflate(ContactListActivity.this,R.layout.tem_contact,null);
             }
            Map<String,String> map=data.get(position);
            TextView nameTV=(TextView) convertView.findViewById(R.id.tv_item_name);
            TextView numberTV=(TextView) convertView.findViewById(R.id.tv_item_number);
             nameTV.setText(map.get("name"));
            numberTV.setText(map.get("number"));

            return convertView;
        }

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
         //得到选择的号码
         String number=data.get(i).get("number");
        Intent intent=getIntent();
        intent.putExtra("NUMBER",number);
        //设置结果
        setResult(RESULT_OK,intent);
        //返回
        finish();
    }
}

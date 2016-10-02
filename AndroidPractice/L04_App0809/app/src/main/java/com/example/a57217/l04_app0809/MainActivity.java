package com.example.a57217.l04_app0809;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ListActivity {

    private ListView lv_main_nameList;
    private BlackContactsAdapter  blackContactsAdapter;
    private BlackContactDao blackContactDao;
    private List<BlackContact> blackContactList;
    private   int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main_nameList=getListView();
         blackContactsAdapter=new BlackContactsAdapter();
        blackContactDao=new BlackContactDao(this);
        blackContactList=blackContactDao.getAllBlackContacts();
        lv_main_nameList.setAdapter(blackContactsAdapter);

        //给listView设置创建contextMenu的长按监听
      lv_main_nameList.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //添加两个item
        menu.add(0,1,0,"更新");
        menu.add(0,2,0,"删除");

        //得到长按的position
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) menuInfo;
            position=  info.position;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
          //得到对应的BlackContact对象
            BlackContact blackContact=blackContactList.get(position);
        switch (item.getItemId()){
            case 1://更新
                    showUpdateDialog(blackContact);
                break;
            case 2: //删除
                         //1.删除数据表对应的数据
                              blackContactDao.deleteBlackContactsById(blackContact.getId());
                        //2.删除list对应的数据
                         blackContactList.remove(position);
                        //3.通知更新列表
                       blackContactsAdapter.notifyDataSetChanged();
                 break;
            default:
                 break;
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(final BlackContact blackContact) {
        final EditText editText=new EditText(this);
        editText.setHint(blackContact.getNumber());
        new AlertDialog.Builder(this)
                .setTitle("更新黑名单")
                .setView(editText)
                .setPositiveButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            //1.更新list对应的数据
                             String number=editText.getText().toString();
                             blackContact.setNumber(number);
                           //2.更新数据表对应的数据
                             blackContactDao.updateBlackContacts(blackContact);
                            //3.通知更新列表
                          blackContactsAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }

    public void addName(View view){
        //1.显示添加的dialog(带输入框)
        final EditText editText=new EditText(this);
        editText.setHint("输入黑名单号");
        new AlertDialog.Builder(this)
                .setTitle("添加黑名单")
                .setView(editText)
                .setPositiveButton("添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                           //1.1保存数据到表中
                          String name=editText.getText().toString();
                        BlackContact blackContact=new BlackContact(-1,name);
                        blackContactDao.addBlackContacts(blackContact);
                          //1.2保存数据到list
                        blackContactList.add(0,blackContact);
                          //1.3通知更新列表
                         blackContactsAdapter.notifyDataSetChanged();;
                    }
                })
                .setNegativeButton("取消",null)
                .show();

    }

    class BlackContactsAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return blackContactList.size();
        }

        @Override
        public Object getItem(int i) {
            return blackContactList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View contentView, ViewGroup viewGroup) {
              if(contentView==null){
                  contentView=View.inflate(MainActivity.this,android.R.layout.simple_list_item_1,null);
              }
             BlackContact blackContact=blackContactList.get(i);
            TextView textView=(TextView) contentView.findViewById(android.R.id.text1);
             textView.setText(blackContact.getNumber());
            return contentView;
        }
    }
}

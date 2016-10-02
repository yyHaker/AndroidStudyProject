package com.example.a57217.app09_provider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 这个程序一直抛出异常：
 *   java.lang.RuntimeException: Unable to start activity ComponentInfo
 *   {com.example.a57217.app09_provider/com.example.a57217.app09_provider.ContactListActivity}:
 *   java.lang.SecurityException: Permission Denial: opening provider com.android.providers.contacts.ContactsProvider2
 *   from ProcessRecord{4b3426a 18645:com.example.a57217.app09_provider/u0a69} (pid=18645, uid=10069)
 *   requires android.permission.READ_CONTACTS or android.permission.WRITE_CONTACTS
 *   可是我已经打开阅读联系人的权限了啊
 */
public class MainActivity extends AppCompatActivity {
    private EditText et_main_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_main_number=(EditText)findViewById(R.id.et_main_number);
    }


    public void toContactList(View view){
     //启动联系人列表页面
         startActivityForResult(new Intent(this,ContactListActivity.class),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1&&resultCode==RESULT_OK){
            //得到返回的number
            String number=data.getStringExtra("NUMBER");
            //显示
            et_main_number.setText(number);
        }
    }
}

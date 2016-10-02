package com.example.a57217.app10_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity{

    private EditText et_main_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_main_content=(EditText)findViewById(R.id.et_main_content);
    }

    public void submit(View view){
            String content=et_main_content.getText().toString();
         if ("".equals(content)){
             Animation animation= AnimationUtils.loadAnimation(this,R.anim.shake);
             et_main_content.startAnimation(animation);
         }else{
             Toast.makeText(this,"请登陆",Toast.LENGTH_SHORT).show();
         }
    }

    public void beginKillVirus(View view){
          startActivity(new Intent(this,KillVirusActivity.class));
    }
}

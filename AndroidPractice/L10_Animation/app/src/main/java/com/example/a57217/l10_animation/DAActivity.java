package com.example.a57217.l10_animation;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class DAActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView iv_da_mm;
    private Button btn_da_start;
    private Button btn_da_stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da);

        iv_da_mm = (ImageView) findViewById(R.id.iv_da_mm);
        btn_da_start = (Button) findViewById(R.id.btn_da_start);
        btn_da_stop = (Button) findViewById(R.id.btn_da_stop);

        btn_da_start.setOnClickListener(this);
        btn_da_stop.setOnClickListener(this);
    }

     private AnimationDrawable animationDrawable;
    @Override
    public void onClick(View view) {
        if (view==btn_da_start){
                if (animationDrawable==null){
                    //得到背景动画图片对象
                     animationDrawable=(AnimationDrawable)iv_da_mm.getBackground();
                    //启动
                    animationDrawable.start();
                }
        }else if (view==btn_da_stop){
              if (animationDrawable!=null){
                  animationDrawable.stop();
                  animationDrawable=null;
              }
        }
    }
}

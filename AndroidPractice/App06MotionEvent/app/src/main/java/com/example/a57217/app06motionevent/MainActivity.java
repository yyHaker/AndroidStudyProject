package com.example.a57217.app06motionevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

       private ImageView lv_main;
       private RelativeLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main=(ImageView) findViewById(R.id.iv_main);
        parentView=(RelativeLayout) lv_main.getParent();

        //设置touch监听
         lv_main.setOnTouchListener(this);
    }

    private  int lastX;
    private int lastY;
    private int maxRight;
    private int maxBottom;

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
          //得到事件的坐标
          int  eventX=(int)motionEvent.getRawX();
          int eventY=(int)motionEvent.getRawY();

          switch (motionEvent.getAction()){
              case MotionEvent.ACTION_DOWN:

                     //得到父视图的right/bottom
                    if(maxRight==0){  //控制只赋值一次
                        maxRight=parentView.getRight();
                        maxBottom=parentView.getBottom();
                    }

                    //第一次记录lastX、lastY
                    lastX=eventX;
                    lastY=eventY;
                   break;
              case MotionEvent.ACTION_MOVE:
                  //计算事件偏移
                   int dx=eventX-lastX;
                  int dy=eventY-lastY;
                   //根据事件的偏移来移动imageView
                  int left=lv_main.getLeft()+dx;
                  int top=lv_main.getTop()+dy;
                  int right=lv_main.getRight()+dx;
                  int bottom=lv_main.getBottom()+dy;

                  //限制left>0
                  if(left<0){
                      right+=-left;
                      left=0;
                  }
                  //限制top>0
                  if (top<0){
                      bottom+=-top;
                      top=0;
                  }
                  //限制right<maxRight
                  if (right>maxRight){
                      left-=right-maxRight;
                      right=maxRight;
                  }
                  //限制bottom<maxBottom
                  if (bottom>maxBottom){
                      top-=bottom-maxBottom;
                      bottom=maxBottom;
                  }

                  lv_main.layout(left,top,right,bottom);
                   //再次记录lastX、lastY
                   lastX=eventX;
                   lastY=eventY;
                  break;
              default:
                  break;
          }
        return true;//返回true，让所有的motionEvent都交给imageView来处理
    }
}

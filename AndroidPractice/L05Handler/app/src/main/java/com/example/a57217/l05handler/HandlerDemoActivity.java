package com.example.a57217.l05handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xml.sax.ErrorHandler;

/**
 * 使用Handler的小DEMO
 * @author yyHaker
1. 手动增加/减少
2. 自动增加/减少
3. 限制数字的最大和最小值 [1,20]
4. 限制Button可操作性
 */
public class HandlerDemoActivity extends AppCompatActivity implements View.OnClickListener {

     private  static  final  int WHAT_INCREASE=1;
     private  static  final  int WHAT_DECREASE=2;

    private TextView tv_demo_number;
    private Button btn_demo_increase;
    private Button btn_demo_decrease;
    private Button btn_demo_pause;

     private Handler handler=new Handler(){
         @Override
         public void handleMessage(Message msg) {
              //得到当前显示的数值
              int number=Integer.parseInt(tv_demo_number.getText().toString());
             switch (msg.what){
                 case WHAT_INCREASE:
                       //限制最大值
                       if(number==20){
                           //设置暂停button不可用
                            btn_demo_pause.setEnabled(false);
                           Toast.makeText(HandlerDemoActivity.this,"已经达到最大值",Toast.LENGTH_SHORT).show();
                           return;
                       }
                      //执行增加操作
                       number++;
                       tv_demo_number.setText(number+"");
                     handler.sendEmptyMessageDelayed(WHAT_INCREASE,1000);
                      break;
                 case WHAT_DECREASE:
                     //限制最小值
                      if (number==1){
                          //设置暂停button不可用
                          btn_demo_pause.setEnabled(false);
                          Toast.makeText(HandlerDemoActivity.this,"已经达到最小值",Toast.LENGTH_SHORT).show();
                          return;
                      }
                      number--;
                     tv_demo_number.setText(number+"");
                     handler.sendEmptyMessageDelayed(WHAT_DECREASE,1000);
                     break;
                 default:
                     break;
             }
         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);
        init();
    }

    private void init() {
        tv_demo_number = (TextView) findViewById(R.id.tv_demo_number);
        btn_demo_increase = (Button) findViewById(R.id.btn_demo_increase);
        btn_demo_decrease = (Button) findViewById(R.id.btn_demo_decrease);
        btn_demo_pause = (Button) findViewById(R.id.btn_demo_pause);

        btn_demo_increase.setOnClickListener(this);
        btn_demo_decrease.setOnClickListener(this);
        btn_demo_pause.setOnClickListener(this);
    }

        @Override
        public void onClick(View view) {
             if (view==btn_demo_increase){
                    //设置Button的可操作性
                     btn_demo_increase.setEnabled(false);
                    btn_demo_decrease.setEnabled(true);
                     btn_demo_pause.setEnabled(true);
                    //停止减少的消息
                    handler.removeMessages(WHAT_DECREASE);
                  //发消息(自动增加,what=1)
                 handler.sendEmptyMessage(WHAT_INCREASE);
             }else if (view==btn_demo_decrease){
                 //设置Button的可操作性
                 btn_demo_increase.setEnabled(true);
                 btn_demo_decrease.setEnabled(false);
                 btn_demo_pause.setEnabled(true);
                    //停止增加的消息
                     handler.removeMessages(WHAT_INCREASE);
                   //发消息(自动减少,what=2)
                  handler.sendEmptyMessage(WHAT_DECREASE);
             }else if (view==btn_demo_pause){
                 //暂停操作
                  handler.removeMessages(WHAT_INCREASE);
                 handler.removeMessages(WHAT_DECREASE);

                 //设置Button的可操作性
                 btn_demo_increase.setEnabled(true);
                 btn_demo_decrease.setEnabled(true);
                 btn_demo_pause.setEnabled(false);
             }
        }
}

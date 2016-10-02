package com.example.a57217.app10_animation;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class KillVirusActivity extends Activity {

    private ImageView iv_killVirus_scan;
    private ProgressBar pb_killVirus_scan;
    private TextView tv_killVirus_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kill_virus);

        iv_killVirus_scan=(ImageView)findViewById(R.id.iv_killVirus_scan);
        pb_killVirus_scan=(ProgressBar)findViewById(R.id.pb_killVirus_scan);
        tv_killVirus_scan=(TextView)findViewById(R.id.tv_killVirus_scan);

        //1.显示动画
         ShowScanAnimation();
        //2.扫描，并显示扫面的进度
          Scanning();
    }

    /**
     * 显示扫描动画
     * iv_killVirus_scan的旋转动画
     */
    public void ShowScanAnimation(){
        //创建动画对象
        RotateAnimation animation=new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //设置
        animation.setDuration(1000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setInterpolator(new LinearInterpolator());
        //启动
        iv_killVirus_scan.startAnimation(animation);
    }

    /**
     * 扫描，并显示扫面的进度
     */
    public void  Scanning(){
        new AsyncTask<Void,Void,Void>(){
            //1.主线程显示提示视图
              @Override
              protected void onPreExecute() {
                  tv_killVirus_scan.setText("手机杀毒引擎正在扫描中");

                  pb_killVirus_scan.setMax(60);
              }
             //2.分线程做长时间的工作(扫描应用)
            @Override
            protected Void doInBackground(Void... voids) {
                int appCount=60;

                for (int i=0;i<appCount;i++){
                    SystemClock.sleep(80);
                    //扫描完成一个发布进度
                   publishProgress();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                pb_killVirus_scan.incrementProgressBy(1);
            }
            //3.主线程更新界面
            @Override
            protected void onPostExecute(Void aVoid) {
                   //隐藏进度条
                  pb_killVirus_scan.setVisibility(View.GONE);
                  //更新文本
                tv_killVirus_scan.setText("扫描完成，未发现病毒，请放心使用");
                  //停止扫描动画
                 iv_killVirus_scan.clearAnimation();
            }
        }.execute();
    }
}

package com.example.a57217.app07_localservice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_main_play;
    private Button btn_main_stop;
    private Button btn_main_pause;
    private Button btn_main_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_play=(Button) findViewById(R.id.btn_main_play);
        btn_main_stop=(Button) findViewById(R.id.btn_main_stop);
        btn_main_pause=(Button) findViewById(R.id.btn_main_pause);
        btn_main_exit=(Button) findViewById(R.id.btn_main_exit);

        btn_main_play.setOnClickListener(this);
        btn_main_stop.setOnClickListener(this);
        btn_main_pause.setOnClickListener(this);
        btn_main_exit.setOnClickListener(this);
    }

    //startService()每次执行会调用onStartCommand()
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,MusicService.class);
          if(view==btn_main_play){
             //playMusic();
              intent.putExtra("action","play");
              startService(intent);
          }else if(view==btn_main_stop){
            // stopMusic();
              intent.putExtra("action","stop");
              startService(intent);
          }else  if (view==btn_main_pause){
            //pauseMusic();
              intent.putExtra("action","pause");
              startService(intent);
          }else if (view==btn_main_exit){
          // exitMusic();
              intent.putExtra("action","exit");
              stopService(intent);//停止服务
              finish();
          }
    }

   /* private MediaPlayer player;
    private  void playMusic(){
        if (player==null){
            player=MediaPlayer.create(this,R.raw.water_hander);
        }
        player.start();
    }

    private   void stopMusic(){
      if (player!=null){
          player.stop();
          player.reset();
          player.release();
          player=null;
      }
    }

    private  void pauseMusic(){
     if (player!=null&&player.isPlaying()){
         player.pause();
       }
    }

    private void exitMusic(){
      stopMusic();
        finish();
    }*/
}

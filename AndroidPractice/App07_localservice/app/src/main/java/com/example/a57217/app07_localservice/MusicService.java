package com.example.a57217.app07_localservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


/**
 * 播放音乐的service
 * Created by 57217 on 2016/8/26.
 */
public class MusicService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private MediaPlayer player;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         String action=intent.getStringExtra("action");
        if(action!=null){
            if (action.equalsIgnoreCase("play")){
                playMusic();
            }else if (action.equalsIgnoreCase("stop")){
                stopMusic();
            }else if (action.equalsIgnoreCase("pause")){
                pauseMusic();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

     //停止服务会调用的方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic();
    }

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

}

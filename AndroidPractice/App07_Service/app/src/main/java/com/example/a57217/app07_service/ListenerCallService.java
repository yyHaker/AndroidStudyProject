package com.example.a57217.app07_service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.lang.reflect.Method;

/**
 *电话监听的service
 * Created by 57217 on 2016/8/26.
 */
public class ListenerCallService extends Service {
    private TelephonyManager tm;
    private PhoneStateListener listener =new PhoneStateListener(){
        /**
         * Callback invoked when device call state changes.
         *
         * @see TelephonyManager#CALL_STATE_IDLE
         * @see TelephonyManager#CALL_STATE_RINGING
         * @see TelephonyManager#CALL_STATE_OFFHOOK
         */
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
             switch (state){
                 case TelephonyManager.CALL_STATE_IDLE:
                     Log.e("TAG","空闲(挂断电话/未来电之前)");
                     break;
                 case TelephonyManager.CALL_STATE_RINGING:
                     Log.e("TAG","响铃");
                     //如果来电电话是110，就挂断电话
                     if ("110".equals(incomingNumber)){
                         //endCall();
                     }
                     break;
                 case TelephonyManager.CALL_STATE_OFFHOOK:
                     Log.e("TAG","接通");
                     break;
                 default:
                     break;
             }
            super.onCallStateChanged(state, incomingNumber);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 挂断电话
     */
    private  void endCall() throws  Exception{
        // 通过反射调用隐藏的API
        // 得到隐藏类的Class对象
        Class c = Class.forName("android.os.ServiceManager");
        // 得到方法所对应的Method对象
        Method method = c.getMethod("getService", String.class);
        // 调用方法
        IBinder iBinder = (IBinder) method.invoke(null,
                Context.TELEPHONY_SERVICE);
        // 得到接口对象
      //  ITelephony telephony = ITelephony.Stub.asInterface(iBinder);
        // 结束通话
      //  telephony.endCall();

    }



    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAG","------service    onCreate()");
        //得到电话管理器
        tm=(TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //监听电话状态
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG","-------service   onDestroy");
        //停止电话监听
        tm.listen(listener,PhoneStateListener.LISTEN_NONE);
    }
}

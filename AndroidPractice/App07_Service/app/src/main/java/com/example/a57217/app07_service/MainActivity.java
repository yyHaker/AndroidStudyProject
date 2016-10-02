package com.example.a57217.app07_service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startCallListener(View view){
        startService(new Intent(this,ListenerCallService.class));
    }
    public void stopCallListener(View view){
        stopService(new Intent(this,ListenerCallService.class));
    }
}

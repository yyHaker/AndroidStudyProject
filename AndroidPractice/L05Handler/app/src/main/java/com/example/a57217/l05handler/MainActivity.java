package com.example.a57217.l05handler;

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

    public void testHandler(View v) {
        startActivity(new Intent(this, HandlerTestActivity.class));
    }

    public void handlerDemo(View v) {
        startActivity(new Intent(this, HandlerDemoActivity.class));
    }

    public void testAsyncTask(View v) {
        startActivity(new Intent(this, AsyncTaskTestActivity.class));
    }
}

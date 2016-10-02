package com.example.a57217.l11graphics;

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

    public void testTuPian(View view) {
        startActivity(new Intent(this, TuPianTestActivity.class));
    }

    public void testDraw(View view) {
        startActivity(new Intent(this, DrawTestActivity.class));
    }
}

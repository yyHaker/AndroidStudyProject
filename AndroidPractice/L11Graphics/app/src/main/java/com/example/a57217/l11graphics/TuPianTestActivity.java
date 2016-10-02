package com.example.a57217.l11graphics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TuPianTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_pian_test);
    }
    public void testBD(View v) {
        startActivity(new Intent(this, BitmapTestActivity.class));
    }

    public void testMatrix(View v) {
        startActivity(new Intent(this, MatrixTestActivity.class));
    }

    public void clickIV(View v) {
        Toast.makeText(this, "点击了selector", 0).show();
    }
}

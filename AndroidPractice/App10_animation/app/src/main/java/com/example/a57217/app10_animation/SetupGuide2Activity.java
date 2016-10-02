package com.example.a57217.app10_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SetupGuide2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_guide2);
    }

    public void previous(View view){
        finish();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }

    public void next(View view){
        startActivity(new Intent(this,SetupGuide3Activity.class));
        overridePendingTransition(R.anim.right_in,R.anim.left_out);
    }
}

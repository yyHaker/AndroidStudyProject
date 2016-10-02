package com.example.a57217.app10_animation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SetupGuide3Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_guide3);
    }

    public void previous(View view){
        finish();
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
    }
    public void finish(View view){
        startActivity(new Intent(this,MainActivity.class));
        overridePendingTransition(R.anim.bottom_in,R.anim.disappear_out);
    }
}

package com.wesoft_eg.myschool.myschool;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run()
            {
                Intent mainIntent = new Intent(getApplicationContext(),Login.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);


    }
}

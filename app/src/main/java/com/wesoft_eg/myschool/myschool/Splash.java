package com.wesoft_eg.myschool.myschool;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wesoft_eg.myschool.myschool.Arab.LoginAR;

import java.util.Locale;

public class Splash extends Activity
{


    String lang = "" ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences sharedPrefv =getSharedPreferences("com.wesoft_eg.myschool.myschool", Context.MODE_PRIVATE);
        lang = sharedPrefv.getString("lang" ,"") ;

        Toast.makeText(getApplicationContext() , lang + "hhhhh" , Toast.LENGTH_LONG ).show();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run()
            {
                if(lang.equals("0"))
                {
                Intent mainIntent = new Intent(getApplicationContext(),Login.class);
                startActivity(mainIntent);
                finish();

                 }
                 else if(lang.equals("1"))
                     {
                         Intent mainIntent = new Intent(getApplicationContext(),LoginAR.class);

                         startActivity(mainIntent);
                         finish();
                     }
                     else
                {

                    Toast.makeText(getApplicationContext() , "0000000000000" ,Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(getApplicationContext(),Login.class);
                    startActivity(mainIntent);
                    finish();
                }
            }
        }, 3000);



    }
}

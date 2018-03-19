package com.wesoft_eg.myschool.myschool;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Login extends Activity
{


    CallbackManager callbackManager ;
    LoginButton loginButton ;


    EditText email ;
    EditText password;


    String str_email ;
    String str_password;




    ProgressDialog mProgressDialog ;

    private static final String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }




    private void init()
    {
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);


        email = (EditText) findViewById(R.id.email) ;
        password = (EditText) findViewById(R.id.pass);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Logon in");
        mProgressDialog.setMessage("we are happy that you are with us");
        mProgressDialog.setCanceledOnTouchOutside(false);

        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;

        if(!loggedIn)
        {
            startActivity(new Intent(getApplicationContext() , MainActivity.class));
        }




        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                startActivity(new Intent(getApplicationContext() , MainActivity.class));
            }

            @Override
            public void onCancel()
            {
                // App code
            }

            @Override
            public void onError(FacebookException exception)
            {
                Log.e("hello", exception.toString());

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void Login(View view)
    {

        mProgressDialog.show();
        str_email = email.getText().toString();
        str_password = password.getText().toString();
        Map<String, String>  params = new HashMap<String, String>();
        params.put("grant_type","password");
        params.put("username", str_email);   //"Admin@Admin.com"
        params.put("password", str_password);    //"Password#1"
        MakeRequest makeRequest = new MakeRequest("Token" , "1" ,params , this);

        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                mProgressDialog.dismiss();
                if(result.get("status").toString().contains("ok"))
                {
                    startActivity(new Intent(getApplicationContext() , MainActivity.class));

                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });





    }

    public void guest(View view) {
    }

    public void register(View view)
    {
        startActivity(new Intent(getApplicationContext() , Registration.class));
    }
}

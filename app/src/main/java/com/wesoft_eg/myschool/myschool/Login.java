package com.wesoft_eg.myschool.myschool;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.wesoft_eg.myschool.myschool.Arab.ForgetPasswordAr;
import com.wesoft_eg.myschool.myschool.Arab.LoginAR;
import com.wesoft_eg.myschool.myschool.Arab.MainActivityAR;
import com.wesoft_eg.myschool.myschool.Arab.RegistrationAR;
import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
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

    String access_token="";


    String lang = "1"; //"0" for eng 1 for arabic




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lang ="0" ;



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
        SharedPreferences sharedPrefv =getSharedPreferences("com.wesoft_eg.myschool.myschool",Context.MODE_PRIVATE);
        access_token = sharedPrefv.getString("access_token" , null) ;
        getLang();

        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;

        if(!loggedIn)
        {
            if(lang.equals("0"))
            {
                Intent intent =new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Intent intent =new Intent(getApplicationContext() , MainActivityAR.class);
                startActivity(intent);
                finish();
            }


        }
        else if(access_token != null)
        {
            if(lang.equals("0"))
            {
                Intent intent =new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Intent intent =new Intent(getApplicationContext() , MainActivityAR.class);
                startActivity(intent);
                finish();
            }
        }




        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                startActivity(new Intent(getApplicationContext() , MainActivity.class));
                finish();
                if(lang.equals("0"))
            {
                Intent intent =new Intent(getApplicationContext() , MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Intent intent =new Intent(getApplicationContext() , MainActivityAR.class);
                startActivity(intent);
                finish();
            }
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

                    String token = result.get("res").toString();
                    try {
                        JSONObject jsonObject = new JSONObject(token);
                        SharedPreferences.Editor editor = getSharedPreferences("com.wesoft_eg.myschool.myschool", MODE_PRIVATE).edit();
                        editor.putString("access_token", jsonObject.getString("access_token"));
                        editor.apply();
                        if(lang.equals("0"))
                        {
                            Intent intent =new Intent(getApplicationContext() , MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            if(lang.equals("0"))
                            {
                                Intent intent =new Intent(getApplicationContext() , Login.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Intent intent =new Intent(getApplicationContext() , LoginAR.class);
                                startActivity(intent);
                                finish();
                            }
                        }



                    } catch (JSONException e)
                    {
                        Toast.makeText(getApplicationContext() , "try to login " , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext() , Login.class));
                        e.printStackTrace();
                    }

                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });





    }

    public void guest(View view)
    {
        if(lang.equals("0"))
        {
            lang="1";
            Intent intent =new Intent(getApplicationContext() , MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            lang="1";
            Intent intent =new Intent(getApplicationContext() , MainActivityAR.class);
            startActivity(intent);
            finish();
        }
    }

    public void register(View view)
    {
        if(lang.equals("0"))
        {
            Intent intent =new Intent(getApplicationContext() , Registration.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent =new Intent(getApplicationContext() , RegistrationAR.class);
            startActivity(intent);
            finish();
        }    }


    public void forgetPassowrd(View view)
    {
        if(lang.equals("0"))
        {
            Intent intent =new Intent(getApplicationContext() , ForgetPassword.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent =new Intent(getApplicationContext() , ForgetPasswordAr.class);
            startActivity(intent);
            finish();
        }

    }

    public void changeLanguage(View view)
    {

        if(lang.equals("0"))
        {
            SharedPreferences.Editor editor = getSharedPreferences("com.wesoft_eg.myschool.myschool", MODE_PRIVATE).edit();
            editor.putString("lang","1" );
            editor.apply();
            lang = "1";
            startActivity(new Intent(getApplicationContext() , LoginAR.class));
            finish();
        }

        else if(lang.equals("1")) {
            SharedPreferences.Editor editor = getSharedPreferences("com.wesoft_eg.myschool.myschool", MODE_PRIVATE).edit();
            editor.putString("lang", "0");
            editor.apply();
            lang = "0";
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }
    }



    public void getLang()
    {
        if (lang == null)
        {
            if (Locale.getDefault().getDisplayLanguage().toString().equals("English"))
            {
                SharedPreferences.Editor editor = getSharedPreferences("com.wesoft_eg.myschool.myschool", MODE_PRIVATE).edit();
                editor.putString("lang", "0");
                editor.apply();
            } else {
                SharedPreferences.Editor editor = getSharedPreferences("com.wesoft_eg.myschool.myschool", MODE_PRIVATE).edit();
                editor.putString("lang", "1");
                editor.apply();
            }
        }


    }
}

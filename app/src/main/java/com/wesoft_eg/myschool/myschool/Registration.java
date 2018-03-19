package com.wesoft_eg.myschool.myschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity
{


    EditText userName;
    EditText phone ;
    EditText passwoed ;
    EditText cPassword ;
    EditText mail;
    RadioGroup radioGroup ;



    String str_userName;
    String str_phone;
    String str_password;
    String str_cPasssowrd ;
    String str_parent;
    String str_mail;


    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();
    }


    void init()
    {
        userName = (EditText) findViewById(R.id.userName);
        phone = (EditText) findViewById(R.id.mobileNumber);
        passwoed = (EditText) findViewById(R.id.password);
        cPassword = (EditText) findViewById(R.id.cPassword);
        mail = (EditText) findViewById(R.id.mail);

        radioGroup = (RadioGroup) findViewById(R.id.radio_groub);



        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Register");
        mProgressDialog.setMessage("we are happy that you are register with us");
        mProgressDialog.setCanceledOnTouchOutside(false);

    }

    public void onRadioButtonClicked(View view)
    {

    }

    public void signUp(View view)
    {

        str_userName = userName.getText().toString() ;
        str_phone = phone.getText().toString();
        str_password = passwoed.getText().toString() ;
        str_cPasssowrd = cPassword.getText().toString();
        str_mail = mail.getText().toString();


        if(radioGroup.getCheckedRadioButtonId() == R.id.radio_ninjas)
        str_parent = "false" ;
        else
            str_parent = "true" ;


        //{"RealName": "Taha El-Husseiny" , "Email":"taha.elssien01y@gmail.com" ,"PhoneNumber":"01095522403" ,"Password":"P@ssw0rd","ConfirmPassword":"P@ssw0rd" , "Gender":true }

        Map<String , String> params = new HashMap<String, String>();

        params.put("RealName", str_userName);
        params.put("Email",str_mail);
        params.put("PhoneNumber",str_phone);
        params.put("Password",str_password);
        params.put("ConfirmPassword",str_cPasssowrd);
        params.put( "Gender",str_parent);

        MakeRequest makeRequest = new MakeRequest("/api/Account/Register" , "1" ,params , this);


        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                mProgressDialog.dismiss();

                Log.e("rrrrrrrr" , result.get("res"));
               /* if(result.get("status").toString().contains("ok"))
                {

                    startActivity(new Intent(getApplicationContext() , MapsActivity.class));
                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();
/*/
            }
        });

    }
}

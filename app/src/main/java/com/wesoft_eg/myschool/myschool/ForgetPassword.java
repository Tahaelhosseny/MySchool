package com.wesoft_eg.myschool.myschool;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPassword extends Activity
{
    String Email ;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        editText = (EditText) findViewById(R.id.mail);
    }

    public void forgetPassowrd(View view)
    {
        Email = editText.getText().toString() ;
        Map<String, String> params = new HashMap<String, String>();
        params.put("Email", Email);   //"Admin@Admin.com"
        MakeRequest makeRequest = new MakeRequest("/api/Account/ForgetPassword" , "1" ,params , this);

        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                if(result.get("status").toString().contains("ok"))
                {

                    String token = result.get("res").toString();
                    try {
                        JSONObject jsonObject = new JSONObject(token);
                        String status = jsonObject.get("Status").toString();
                        if(status.equals("Ok"))
                        {
                            Toast.makeText(getApplicationContext(), jsonObject.get("Message").toString(), Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else if (status.equals("No"))
                        {
                            Toast.makeText(getApplicationContext(), jsonObject.get("Message").toString(), Toast.LENGTH_LONG).show();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });
    }
}

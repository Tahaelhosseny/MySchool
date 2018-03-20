package com.wesoft_eg.myschool.myschool.Arab;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.wesoft_eg.myschool.myschool.R;
import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordAr extends Activity
{
    String Email ;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_ar);
        editText = (EditText) findViewById(R.id.mail);

    }

   public void forgetPassowrd(View view)
    {
        Email = editText.getText().toString() ;
        if(isValidEmail(Email))
        {
            Toast.makeText(getApplicationContext() , "ليس بريد الكترونى صحيح" , Toast.LENGTH_LONG).show();
        }
        else
            {


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
                                    Toast.makeText(getApplicationContext(), "تم ارسال رساله الى بريدك الالكترونى الرجاء الاطلاع عليه ", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                                else if (status.equals("No"))
                                {
                                    Toast.makeText(getApplicationContext(), "تأكد من البريد الالكترونى وحاول مره اخرى", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        else
                            Toast.makeText(getApplicationContext() ,"هناك خطأ ما حاول مره اخرى ",Toast.LENGTH_SHORT).show();

                    }
                });
            }
    }

    public final static boolean isValidEmail(CharSequence target)
    {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}

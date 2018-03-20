package com.wesoft_eg.myschool.myschool.Arab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wesoft_eg.myschool.myschool.Login;
import com.wesoft_eg.myschool.myschool.MainActivity;
import com.wesoft_eg.myschool.myschool.R;
import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationAR extends AppCompatActivity
{

    EditText userName;
    EditText phone ;
    EditText passwoed ;
    EditText cPassword ;
    EditText mail;
    RadioGroup radioGroup ;
    RadioButton dad ;
    RadioButton mom ;



    String str_userName = "";
    String str_phone= "";
    String str_password= "";
    String str_cPasssowrd = "";
    String str_parent= "true";
    String str_mail= "";


    ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_ar);
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
        dad = (RadioButton) findViewById(R.id.dad);
        mom = (RadioButton) findViewById(R.id.mom);

        if(str_parent.equals("true"))
            dad.setChecked(true);
        else
            mom.setChecked(true);


        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("تسجيل البينات");
        mProgressDialog.setMessage("سعدنا بوجودك معنا");
        mProgressDialog.setCanceledOnTouchOutside(false);



    }

    public void onRadioButtonClicked(View view)
    {

    }

    public void signUp(View view)
    {

        int i = 8;

        if(radioGroup.getCheckedRadioButtonId() == R.id.radio_ninjas)
            str_parent = "false" ;
        else
            str_parent = "true" ;

        str_userName = userName.getText().toString() ;
        str_phone = phone.getText().toString();
        str_password = passwoed.getText().toString();
        str_cPasssowrd = cPassword.getText().toString();
        str_mail = mail.getText().toString();

        if(str_userName.isEmpty())
        {
            userName.setError("هذا حقل فارغ ");
            --i;
        }
        if(!isValidEmail(str_mail))
        {
            mail.setError("اعد كتابه البريد الالكترونى بشكل صحيح");
            --i;
        }

        if(!validCellPhone(str_phone))
        {
            phone.setError("رقم هاتف غير صحيح");
            --i;

        }

        if(str_password.isEmpty())
        {
            passwoed.setError("هذا حقل فارغ ");
            --i;


        }
        if(str_password.length()<6)
        {
            passwoed.setError("هذا الحقل يجب ان يحتوى اكثر نم 6 رموز ");
            --i;
        }
        if(str_cPasssowrd.length()<6)
        {
            cPassword.setError("هذا الحقل يجب ان يحتوى اكثر نم 6 رموز");
            --i;
        }
        if (str_cPasssowrd.isEmpty())
        {
            cPassword.setError("هذا حقل فارغ");
            --i;

        }

        if(!str_cPasssowrd.equals(str_password))
        {
            passwoed.setError( "كلمتا المرور لا تنطبقان");
            cPassword.setError("كلمتا المرور لا تنطبقان");
            --i;

        }

        if(i==8)
        {
            //{"Email": "eng.abbasmohamed14@gmail.com" , "Password":"P@ssw0rd1" , "ConfirmPassword":"P@ssw0rd1" , "RealName": "Full Stack Developer", "PhoneNumber":"41878","Gender":true}

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
                    if(result.get("status").toString().contains("ok"))
                    {
                        Toast.makeText(getApplicationContext() ,result.get("res").toString(),Toast.LENGTH_SHORT).show();
                        login();
                    }
                    else
                        Toast.makeText(getApplicationContext() ,"هنالك شئ غير صحيح من فضلك اعد المحاوله",Toast.LENGTH_SHORT).show();

                }
            });

        }

        //{"RealName": "Taha El-Husseiny" , "Email":"taha.elssien01y@gmail.com" ,"PhoneNumber":"01095522403" ,"Password":"P@ssw0rd","ConfirmPassword":"P@ssw0rd" , "Gender":true }


    }



    public boolean validCellPhone(String number)
    {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }



    public final static boolean isValidEmail(CharSequence target)
    {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }


    private  void  login ()
    {
        mProgressDialog.show();
        Map<String, String>  params = new HashMap<String, String>();
        params.put("grant_type","password");
        params.put("username", str_mail);   //"Admin@Admin.com"
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
                        startActivity(new Intent(getApplicationContext() , MainActivity.class));
                        finish();

                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext() , "اعد تسجيل الدخول مره اخرى " , Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext() , Login.class));

                    }


                }
                else
                    Toast.makeText(getApplicationContext() ,"هنالك شئ غير صحيح من فضلك اعد المحاوله",Toast.LENGTH_SHORT).show();

            }
        });

    }
}

package com.wesoft_eg.myschool.myschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Filter extends AppCompatActivity
{
    Spinner country ;
    Spinner city ;
    Spinner area ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        init();

    }

    private void init()
    {
        country = (Spinner) findViewById(R.id.country);
        ArrayAdapter<?> countryAdap = ArrayAdapter.createFromResource(this , R.array._Country , android.R.layout.simple_spinner_item );

        countryAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        country.setAdapter(countryAdap);



        city = (Spinner) findViewById(R.id.city);
        ArrayAdapter<?> cityAdap = ArrayAdapter.createFromResource(this , R.array.city , android.R.layout.simple_spinner_item );

        cityAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        city.setAdapter(cityAdap);

        area = (Spinner) findViewById(R.id.area);
        ArrayAdapter<?> areaAdap = ArrayAdapter.createFromResource(this , R.array.area , android.R.layout.simple_spinner_item );

        areaAdap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        area.setAdapter(areaAdap);

        getCountry();


    }


    private void getCountry()
    {
        MakeRequest makeRequest = new MakeRequest("/Api/values/GetCountries" , "0" , this);

        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
               // mProgressDialog.dismiss();
                if(result.get("status").toString().contains("ok"))
                {

                    Toast.makeText(getApplicationContext() ,result.get("res").toString(),Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext() , MainActivity.class));

                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void onRadioButtonClicked(View view) {
    }
}

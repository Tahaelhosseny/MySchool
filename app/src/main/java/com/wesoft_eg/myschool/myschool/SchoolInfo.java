package com.wesoft_eg.myschool.myschool;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wesoft_eg.myschool.myschool.fragmentsProfile.AboutFragment;
import com.wesoft_eg.myschool.myschool.fragmentsProfile.FeaturesFragment;
import com.wesoft_eg.myschool.myschool.fragmentsProfile.GalleryFragment;
import com.wesoft_eg.myschool.myschool.fragmentsProfile.SectorFragment;
import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import java.util.Map;

public class SchoolInfo extends AppCompatActivity
{



    String id ;
    String isSchool ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info);

        Intent intent = getIntent();

        id = intent.getStringExtra("id") ;
        isSchool = intent.getStringExtra("isSchool");
        Toast.makeText(getApplicationContext() , "kkkkkkk\n"+id+ "\n" + isSchool,Toast.LENGTH_SHORT).show();

        getData();
        init();

    }


    private void getData ()
    {
        MakeRequest makeRequest = new MakeRequest("/api/values/SchoolKidsCenterProfile?Id="+id.toString() +"&IsSchool="+isSchool.toString() , "0"  , this);

        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
               // mProgressDialog.dismiss();
                if(result.get("status").toString().contains("ok"))
                {
                    Toast.makeText(getApplicationContext() , result.get("res").toString() , Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init ()
    {
        FragmentManager  fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        AboutFragment aboutFragment = new AboutFragment();
        fragmentTransaction.add(R.id.container,aboutFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();




    }

    public void About(View view)
    {
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=  fragmentManager.beginTransaction();
        AboutFragment aboutFragment = new AboutFragment();
        fragmentTransaction.replace(R.id.container,aboutFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void gallery(View view)
    {
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
        GalleryFragment galleryFragment = new GalleryFragment();
        fragmentTransaction.replace(R.id.container,galleryFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void features(View view)
    {
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=  fragmentManager.beginTransaction();
        FeaturesFragment featuresFragment = new FeaturesFragment();
        fragmentTransaction.replace(R.id.container,featuresFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }


    public void sector(View view)
    {
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction=  fragmentManager.beginTransaction();
        SectorFragment sectorFragment = new SectorFragment();
        fragmentTransaction.replace(R.id.container,sectorFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }



}

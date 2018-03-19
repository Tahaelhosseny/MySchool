package com.wesoft_eg.myschool.myschool.fragmentsProfile;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wesoft_eg.myschool.myschool.R;
import com.wesoft_eg.myschool.myschool.showSchoolLocation;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment
{

    Button button ;

    String id ;
    String isSchool ;

    private void getData(String id , String isSchool)
    {
        this.id = id ;
        this.isSchool = isSchool ;
    }

    public AboutFragment()
    {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_about, container, false) ;

        button = (Button) view.findViewById(R.id.openMap);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //startActivity(new Intent(getActivity(),showSchoolLocation.class));

                startActivityForResult(new Intent(getActivity(),showSchoolLocation.class),10);

            }
        });

        return view;
    }

}

package com.wesoft_eg.myschool.myschool.MOdel;

import java.io.Serializable;

/**
 * Created by Taha on 3/24/2018.
 */

public class Distrect implements Serializable
{
    String DistrictId;
    String Title ;


    public Distrect(String districtId, String title)
    {
        DistrictId = districtId;
        Title = title;
    }

    public Distrect()
    {
    }

    public String getDistrictId() {
        return DistrictId;
    }

    public void setDistrictId(String districtId) {
        DistrictId = districtId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


    @Override
    public String toString() {
        return Title;
    }
}

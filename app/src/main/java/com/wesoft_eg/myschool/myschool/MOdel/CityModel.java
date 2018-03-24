package com.wesoft_eg.myschool.myschool.MOdel;

import java.io.Serializable;

/**
 * Created by Taha on 3/24/2018.
 */

public class CityModel implements Serializable
{
    String CityId ;
    String Title ;


    public CityModel(String cityId, String title)
    {
        CityId = cityId;
        Title = title;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
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

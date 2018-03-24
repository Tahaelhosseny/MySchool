package com.wesoft_eg.myschool.myschool.MOdel;

import java.io.Serializable;

/**
 * Created by Taha on 3/24/2018.
 */

public class CountryModel implements Serializable
{
    String CountryId ;
    String Title ;
    String CountryCode ;


    public CountryModel()
    {
    }

    public CountryModel(String countryId, String title, String countryCode)
    {
        CountryId = countryId;
        Title = title;
        CountryCode = countryCode;
    }


    public String getCountryId()
    {
        return CountryId;
    }

    public void setCountryId(String countryId)
    {
        CountryId = countryId;
    }

    public String getTitle()
    {
        return Title;
    }

    public void setTitle(String title)
    {
        Title = title;
    }

    public String getCountryCode()
    {
        return CountryCode;
    }

    public void setCountryCode(String countryCode)
    {
        CountryCode = countryCode;
    }

    @Override
    public String toString()
    {
        return Title;
    }
}

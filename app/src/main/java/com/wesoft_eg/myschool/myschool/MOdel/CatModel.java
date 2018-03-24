package com.wesoft_eg.myschool.myschool.MOdel;

import java.io.Serializable;

/**
 * Created by Taha on 3/24/2018.
 */

public class CatModel implements Serializable
{
    String CategoryId ;
    String Title ;
    String IsSchool ;
    String IsKg ;

    public CatModel(String categoryId, String title, String isSchool, String isKg)
    {
        CategoryId = categoryId;
        Title = title;
        IsSchool = isSchool;
        IsKg = isKg;
    }


    public CatModel()
    {
    }

    public String getCategoryId()
    {
        return CategoryId;
    }

    public void setCategoryId(String categoryId)
    {
        CategoryId = categoryId;
    }

    public String getTitle()
    {
        return Title;
    }

    public void setTitle(String title)
    {
        Title = title;
    }

    public String getIsSchool()
    {
        return IsSchool;
    }

    public void setIsSchool(String isSchool)
    {
        IsSchool = isSchool;
    }

    public String getIsKg()
    {
        return IsKg;
    }

    public void setIsKg(String isKg)
    {
        IsKg = isKg;
    }

    @Override
    public String toString() {
        return  Title ;
    }
}

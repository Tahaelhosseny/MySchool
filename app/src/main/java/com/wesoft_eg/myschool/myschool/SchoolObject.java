package com.wesoft_eg.myschool.myschool;

/**
 * Created by Taha on 3/13/2018.
 */

public class SchoolObject
{
    String schoolId;
    String Title;
    String TitleAr;
    String CategoryId;
    String SubcategoryId;
    String IsSchool;
    String Rate;
    String Priority;
    String Lat;
    String Long;

    String markerId;

    public String getMarkerId() {
        return markerId;
    }

    public void setMarkerId(String markerId) {
        this.markerId = markerId;
    }

    public SchoolObject(String schoolId, String title, String titleAr, String categoryId, String subcategoryId, String isSchool, String rate, String priority, String lat, String aLong)
    {
        this.schoolId = schoolId;
        Title = title;
        TitleAr = titleAr;
        CategoryId = categoryId;
        SubcategoryId = subcategoryId;
        IsSchool = isSchool;
        Rate = rate;
        Priority = priority;
        Lat = lat;
        Long = aLong;
    }


    public SchoolObject() {}


    public SchoolObject(String schoolId)
    {
        this.schoolId = schoolId ;
    }


    public String getSchoolId()
    {
        return schoolId;
    }

    public void setSchoolId(String schoolId)
    {
        this.schoolId = schoolId;
    }

    public String getTitle()
    {
        return Title;
    }

    public void setTitle(String title)
    {
        Title = title;
    }

    public String getTitleAr()
    {
        return TitleAr;
    }

    public void setTitleAr(String titleAr)
    {
        TitleAr = titleAr;
    }

    public String getCategoryId()
    {
        return CategoryId;
    }

    public void setCategoryId(String categoryId)
    {
        CategoryId = categoryId;
    }

    public String getSubcategoryId()
    {
        return SubcategoryId;
    }

    public void setSubcategoryId(String subcategoryId)
    {
        SubcategoryId = subcategoryId;
    }

    public String getIsSchool()
    {
        return IsSchool;
    }

    public void setIsSchool(String isSchool)
    {
        IsSchool = isSchool;
    }

    public String getRate()
    {
        return Rate;
    }

    public void setRate(String rate)
    {
        Rate = rate;
    }

    public String getPriority()
    {
        return Priority;
    }

    public void setPriority(String priority)
    {
        Priority = priority;
    }

    public String getLat()
    {
        return Lat;
    }

    public void setLat(String lat)
    {
        Lat = lat;
    }

    public String getLong()
    {
        return Long;
    }

    public void setLong(String aLong)
    {
        Long = aLong;
    }
}

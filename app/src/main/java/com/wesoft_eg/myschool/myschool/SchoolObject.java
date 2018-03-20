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

    String CategoryTitleAr;
    String SubCategoryTitleAr ;


    String CategoryTitle ;
    String SubCategoryTitle;


    public SchoolObject(String schoolId, String title, String titleAr, String categoryId, String subcategoryId, String isSchool, String rate, String priority, String lat, String aLong, String markerId, String categoryTitleAr, String subCategoryTitleAr, String categoryTitle, String subCategoryTitle) {
        this.schoolId = schoolId;
        this.Title = title;
        this.TitleAr = titleAr;
        this.CategoryId = categoryId;
        this.SubcategoryId = subcategoryId;
        this.IsSchool = isSchool;
        this.Rate = rate;
        this.Priority = priority;
        this.Lat = lat;
        this.Long = aLong;
        this.markerId = markerId;
        this.CategoryTitleAr = categoryTitleAr;
        this.SubCategoryTitleAr = subCategoryTitleAr;
        this.CategoryTitle = categoryTitle;
        this.SubCategoryTitle = subCategoryTitle;
    }


    //arab
    public SchoolObject(String schoolId, String titleAr, String isSchool, String rate, String priority, String lat, String aLong, String categoryTitleAr, String subCategoryTitleAr , String arab)
    {
        this.schoolId = schoolId;
        this.TitleAr = titleAr;
        this.IsSchool = isSchool;
        this.Rate = rate;
        this.Priority = priority;
        this.Lat = lat;
        this.Long = aLong;
        this.CategoryTitleAr = categoryTitleAr;
        this.SubCategoryTitleAr = subCategoryTitleAr;
    }


    //eng
    public SchoolObject(String schoolId, String titleAr, String isSchool, String rate, String priority, String lat, String aLong, String CategoryTitle, String SubCategoryTitle )
    {
        this.schoolId = schoolId;
        this.TitleAr = titleAr;
        this.IsSchool = isSchool;
        this.Rate = rate;
        this.Priority = priority;
        this.Lat = lat;
        this.Long = aLong;
        this.CategoryTitleAr =CategoryTitle ;
        this.SubCategoryTitleAr = SubCategoryTitle;
    }




    public String getMarkerId() {
        return markerId;
    }

    public void setMarkerId(String markerId) {
        this.markerId = markerId;
    }


    public String getCategoryTitleAr() {
        return CategoryTitleAr;
    }

    public void setCategoryTitleAr(String categoryTitleAr) {
        CategoryTitleAr = categoryTitleAr;
    }

    public String getSubCategoryTitleAr() {
        return SubCategoryTitleAr;
    }

    public void setSubCategoryTitleAr(String subCategoryTitleAr) {
        SubCategoryTitleAr = subCategoryTitleAr;
    }

    public String getCategoryTitle() {
        return CategoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        CategoryTitle = categoryTitle;
    }

    public String getSubCategoryTitle() {
        return SubCategoryTitle;
    }

    public void setSubCategoryTitle(String subCategoryTitle) {
        SubCategoryTitle = subCategoryTitle;
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

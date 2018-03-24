package com.wesoft_eg.myschool.myschool.MOdel;

import java.io.Serializable;

/**
 * Created by Taha on 3/24/2018.
 */

public class SubCatModel implements Serializable
{
    String SubCategoryId ;
    String Title;

    public SubCatModel(String subCategoryId, String title)
    {
        SubCategoryId = subCategoryId;
        Title = title;
    }

    public String getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        SubCategoryId = subCategoryId;
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

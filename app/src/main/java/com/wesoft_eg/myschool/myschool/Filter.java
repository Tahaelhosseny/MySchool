package com.wesoft_eg.myschool.myschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.wesoft_eg.myschool.myschool.MOdel.CatModel;
import com.wesoft_eg.myschool.myschool.MOdel.CityModel;
import com.wesoft_eg.myschool.myschool.MOdel.CountryModel;
import com.wesoft_eg.myschool.myschool.MOdel.Distrect;
import com.wesoft_eg.myschool.myschool.MOdel.SubCatModel;
import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter extends AppCompatActivity
{
    Spinner country ;
    Spinner city ;
    Spinner area ;
    Spinner cat ;
    Spinner sub_Cat;
    EditText search_name ;

    List<CountryModel> countryModels ;
    List<CityModel> cityModels ;
    List<Distrect> distrects ;
    List<CatModel> catModels ;
    List<SubCatModel> subCatModels;
    ArrayAdapter<CountryModel> countryArrayAdapter ;
    ArrayAdapter<CityModel> cityModelArrayAdapter ;
    ArrayAdapter<Distrect> distrectArrayAdapter;
    ArrayAdapter<CatModel> catModelArrayAdapter;
    ArrayAdapter<SubCatModel> subCatModelArrayAdapter;

    String isSchool = "true" ;

    RadioGroup radioGroup ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        init();
        get_cat();
    }

    private void init()
    {
        search_name = (EditText) findViewById(R.id.search_name);
        country = (Spinner) findViewById(R.id.country);
        city = (Spinner) findViewById(R.id.city);
        area = (Spinner) findViewById(R.id.area);
        cat = (Spinner) findViewById(R.id.cat);
        sub_Cat = (Spinner ) findViewById(R.id.sub_cat);
        radioGroup = (RadioGroup) findViewById(R.id.radio_groub);
        countryModels = new ArrayList<>();
        cityModels = new ArrayList<>();
        distrects = new ArrayList<>();
        catModels = new ArrayList<>();
        subCatModels = new ArrayList<>() ;
        countryArrayAdapter = new ArrayAdapter<CountryModel>(this,android.R.layout.simple_spinner_item,countryModels);
        cityModelArrayAdapter = new ArrayAdapter<CityModel>(this,android.R.layout.simple_spinner_item,cityModels);
        distrectArrayAdapter = new ArrayAdapter<Distrect>(this,android.R.layout.simple_spinner_item,distrects);
        catModelArrayAdapter = new ArrayAdapter<CatModel>(this,android.R.layout.simple_spinner_item,catModels);
        subCatModelArrayAdapter = new ArrayAdapter<SubCatModel>(this,android.R.layout.simple_spinner_item,subCatModels);
        country.setAdapter(countryArrayAdapter);
        city.setAdapter(cityModelArrayAdapter);
        area.setAdapter(distrectArrayAdapter);
        cat.setAdapter(catModelArrayAdapter);
        sub_Cat.setAdapter(subCatModelArrayAdapter);
        countryArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distrectArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subCatModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryModels.add(new CountryModel("-1" ,"select country..." , "-1"));
        cityModels.add(new CityModel("-1" , "select city..."));
        distrects.add(new Distrect("-1" , "select district..."));
        catModels.add(new CatModel("-1","select category...","",""));
        subCatModels.add(new SubCatModel("-1","select sub_category..."));
        countryArrayAdapter.notifyDataSetChanged();
        cityModelArrayAdapter.notifyDataSetChanged();
        distrectArrayAdapter.notifyDataSetChanged();
        catModelArrayAdapter.notifyDataSetChanged();
        subCatModelArrayAdapter.notifyDataSetChanged();
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
               if(!countryModels.get(i).getCountryId().equals("-1"))
               {
                   getCites(countryModels.get(i).getCountryId());
                   cityModels.clear();
                   distrects.clear();
                   cityModels.add(new CityModel("-1" , "select city..."));
                   distrects.add(new Distrect("-1" , "select district..."));
                   cityModelArrayAdapter.notifyDataSetChanged();
                   distrectArrayAdapter.notifyDataSetChanged();
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(!cityModels.get(i).getCityId().equals("-1"))
                {
                    getDistrect(cityModels.get(i).getCityId());
                    distrects.clear();
                    distrects.add(new Distrect("-1" , "select district..."));
                    distrectArrayAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(!distrects.get(i).getDistrictId().equals("-1"))
                {
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
        cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String id = catModels.get(i).getCategoryId();
                getSubCat(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                if(radioGroup.getCheckedRadioButtonId() == R.id.school)
                {
                    isSchool ="true";
                    search_name.setHint("school name");
                }
                else
                {
                    search_name.setHint("kg name");
                    isSchool = "false";
                }
                get_cat();

            }
        });
        getCountry();
    }

    private void getSubCat(String id)
    {
        subCatModels.clear();
        subCatModels.add(new SubCatModel("-1","select sub_category..."));
        subCatModelArrayAdapter.notifyDataSetChanged();

        final Map<String, String> params = new HashMap<String, String>();

        MakeRequest makeRequest = new MakeRequest("api/Values/EngGetSubCategories?categoryId="+id , "0" , params , this);

        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                if(result.get("status").toString().contains("ok"))
                {
                    try {
                        JSONArray jsonArray = new JSONArray(result.get("res"));
                        for (int i = 0 ; i <jsonArray.length() ; i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            subCatModels.add(new SubCatModel(jsonObject.getString("SubCategoryId") , jsonObject.getString("Title")));
                        }
                        subCatModelArrayAdapter.notifyDataSetChanged();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getDistrect(String city)
    {
        final Map<String, String> params = new HashMap<String, String>();

        MakeRequest makeRequest = new MakeRequest("/api/Values/EngGetDistricts?cityId="+city , "0" , params , this);

        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                if(result.get("status").toString().contains("ok"))
                {
                    try {
                        JSONArray jsonArray = new JSONArray(result.get("res"));
                        for (int i = 0 ; i <jsonArray.length() ; i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            distrects.add(new Distrect(jsonObject.getString("DistrictId") , jsonObject.getString("Title")));
                        }
                        distrectArrayAdapter.notifyDataSetChanged();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getCites(String countryId)
    {
        final Map<String, String> params = new HashMap<String, String>();

        MakeRequest makeRequest = new MakeRequest("/api/Values/EngGetCities?countryId="+countryId , "0" , params , this);

        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                if(result.get("status").toString().contains("ok"))
                {
                    try {
                        JSONArray jsonArray = new JSONArray(result.get("res"));
                        for (int i = 0 ; i <jsonArray.length() ; i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            cityModels.add(new CityModel(jsonObject.getString("CityId") , jsonObject.getString("Title")));
                        }
                        cityModelArrayAdapter.notifyDataSetChanged();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getCountry()
    {
        final Map<String, String> params = new HashMap<String, String>();

        MakeRequest makeRequest = new MakeRequest("/api/Values/EngGetCountries" , "0" , params , this);

        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                if(result.get("status").toString().contains("ok"))
                {
                    try {
                        JSONArray jsonArray = new JSONArray(result.get("res"));
                        for (int i = 0 ; i <jsonArray.length() ; i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            countryModels.add(new CountryModel(jsonObject.getString("CountryId") , jsonObject.getString("Title") , jsonObject.getString("CountryCode")));

                        }
                        countryArrayAdapter.notifyDataSetChanged();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void onRadioButtonClicked(View view) {
    }



    private void get_cat()
    {
        catModels.clear();
        catModels.add(new CatModel("-1","select category","",""));
        catModelArrayAdapter.notifyDataSetChanged();

        final Map<String, String> params = new HashMap<String, String>();

        MakeRequest makeRequest = new MakeRequest("api/Values/EngGetFilteredCategories?IsSchool="+isSchool , "0" , params , this);

        makeRequest.request( new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                if(result.get("status").toString().contains("ok"))
                {
                    try {
                        JSONArray jsonArray = new JSONArray(result.get("res"));
                        for (int i = 0 ; i <jsonArray.length() ; i++)
                        {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            catModels.add(new CatModel(jsonObject.getString("CategoryId") , jsonObject.getString("Title") , jsonObject.getString("IsSchool") ,jsonObject.getString("IsKg")));
                        }
                        catModelArrayAdapter.notifyDataSetChanged();
                    } catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();

            }
        });
    }



    private void applyFilters()
    {

        String str_NameSearch = search_name.getText().toString();
        String str_countryId =countryModels.get(5).getCountryId();
        String str_cityId;
        String str_isSchool;
        String str_distirictId;
        String str_categoryId;
        String subCategoryId;

        final Map<String, String> params = new HashMap<String, String>();
        params.put("NameSearch",str_NameSearch);
        params.put("countryId",str_countryId);
        //params.put("cityId",str_cityId);
        //params.put("distirictId",str_distirictId);
        //params.put("isSchool",str_isSchool);
        //params.put("categoryId",str_categoryId);
        //params.put("subCategoryId",subCategoryId);
    }
}

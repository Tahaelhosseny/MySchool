package com.wesoft_eg.myschool.myschool.Arab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wesoft_eg.myschool.myschool.Filter;
import com.wesoft_eg.myschool.myschool.MainActivity;
import com.wesoft_eg.myschool.myschool.R;
import com.wesoft_eg.myschool.myschool.SchoolInfo;
import com.wesoft_eg.myschool.myschool.SchoolObject;
import com.wesoft_eg.myschool.myschool.netHelper.MakeRequest;
import com.wesoft_eg.myschool.myschool.netHelper.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivityAR extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener ,OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks ,GoogleApiClient.OnConnectionFailedListener
{

    private GoogleMap mMap;
    SupportMapFragment mapFragment ;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;

    GoogleApiClient mGoogleApiClient;
    LocationRequest locationRequest;

    Location mLocation ;


    ArrayList<SchoolObject> schoolList = new ArrayList<SchoolObject>();


    String access_token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
        setContentView(R.layout.activity_main_ar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPrefv =getSharedPreferences("com.wesoft_eg.myschool.myschool", Context.MODE_PRIVATE);
        access_token = sharedPrefv.getString("access_token" , null) ;
        Toast.makeText(getApplicationContext() , access_token ,Toast.LENGTH_LONG).show();
        init();

    }

    private void init()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);




        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        else if (id == R.id.filter)
        {
            startActivityForResult(new Intent(getApplicationContext(),FilterAr.class) ,5);
        }
        else if(id == R.id.lang)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        else if(id==R.id.Logout)
        {
            logOut();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logOut()
    {
        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;

        if(!loggedIn)
        {
            LoginManager.getInstance().logOut();
            Toast.makeText(getApplicationContext() , "you Logged Out Successfully" , Toast.LENGTH_LONG).show();
            finish();

        }
        else if(!access_token.isEmpty())
        {
            SharedPreferences sharedPref =getSharedPreferences("com.wesoft_eg.myschool.myschool",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove("access_token");
            editor.apply();
            Toast.makeText(getApplicationContext() , "you Logged Out Successfully" , Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home)
        {

        } else if (id == R.id.contact)
        {

        } else if (id == R.id.about)
        {

        } else if (id == R.id.sign_out)
        {

        } else if (id == R.id.maccount)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {



        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(26.1377318, 50.5281543);
        mMap.addMarker(new MarkerOptions().position(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.1377318, 50.5281543),12));

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter()
        {
            @Override
            public View getInfoWindow(Marker marker)
            {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker)
            {

                View v = getLayoutInflater().inflate(R.layout.map_info_window_layout, null);
                TextView title = (TextView) v.findViewById(R.id.tvTitle);
                Button details = (Button) v.findViewById(R.id.btn_details);
                title.setText(marker.getTitle());


                //title.setText();
                return v;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {
            @Override
            public void onInfoWindowClick(Marker marker)
            {

                String isSchool = "";
                String id ="";

                for(int i =0 ; i<schoolList.size() ;i++)
                {
                    if(marker.getId().equals(schoolList.get(i).getMarkerId()))
                    {
                        isSchool = schoolList.get(i).getIsSchool();
                        id =  schoolList.get(i).getSchoolId() ;
                        break;
                    }
                }

                Toast.makeText(getApplicationContext(),  isSchool + "\n" + id, Toast.LENGTH_SHORT).show();

                if(!isSchool.isEmpty()&&!id.isEmpty())
                {
                    startActivity(new Intent(getApplicationContext() , SchoolInfo.class).putExtra("id",id).putExtra("isSchool",isSchool));
                }


                // startActivity(new Intent(getApplicationContext() , SchoolInfo.class));
            }
        });

        buildGooGleApiClient();
    }

    void request(String lat , String lang)
    {
        final Map<String, String> params = new HashMap<String, String>();


        String link = "/api/Values/ArGPSLocationSearch?latitude=" + lat + "&longitude=" + lang ;
        MakeRequest makeRequest = new MakeRequest(link , "0" ,params , this);

        makeRequest.request(new VolleyCallback()
        {
            @Override
            public void onSuccess(Map<String, String> result)
            {
                if(result.get("status").toString().contains("ok"))
                {

                    parceData(result);
                }
                else
                    Toast.makeText(getApplicationContext() ,"something go wrong try again",Toast.LENGTH_SHORT).show();
            }


        });
    }




    protected synchronized void buildGooGleApiClient()
    {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();
    }


    @Override
    public void onConnected(Bundle bundle)
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient , locationRequest ,  this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location)
    {
        if(mLocation== null)
        {
            mLocation = location;
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()),12));
            request(String.valueOf(mLocation.getLatitude()),String.valueOf(mLocation.getLongitude()));
        }
    }
    private void parceData(Map<String, String> result)
    {
        String responce = result.get("res").toString();
        schoolList.clear();
        try {
            JSONObject jsonObject = new JSONObject(responce.toString());

            String schools = jsonObject.getString("schools");

            JSONArray schoolJsonArray1 = new JSONArray(schools);
            for (int i=0 ; i < schoolJsonArray1.length() ; i++)
            {
                JSONObject jsonObject1 = schoolJsonArray1.getJSONObject(i);
                // SchoolObject schoolObject = new SchoolObject(jsonObject1.getString("schoolId"),jsonObject1.getString("Title"),jsonObject1.getString("TitleAr"),jsonObject1.getString("CategoryId"),jsonObject1.getString("SubcategoryId"),jsonObject1.getString("IsSchool"),jsonObject1.getString("Rate"),jsonObject1.getString("Priority"),jsonObject1.getString("Lat"),jsonObject1.getString("Long"));
//              (schoolId,  titleAr,  isSchool,  rate,  priority,  lat,  aLong,  CategoryTitle,  SubCategoryTitle )
                SchoolObject schoolObject = new SchoolObject(jsonObject1.getString("Id").toString(),jsonObject1.getString("Title").toString(),jsonObject1.getString("IsSchool").toString(),jsonObject1.getString("Rate").toString(),jsonObject1.getString("Priority").toString(),jsonObject1.getString("Lat").toString(),jsonObject1.getString("Long").toString() ,jsonObject1.getString("CategoryTitle").toString(),jsonObject1.getString("SubCategoryTitle").toString());
                schoolList.add(schoolObject);
            }
            String kids = jsonObject.getString("kidsCenters");

            JSONArray kidsJsonArray1 = new JSONArray(kids);

            for (int i=0 ; i < kidsJsonArray1.length() ; i++)
            {
                JSONObject jsonObject1 = kidsJsonArray1.getJSONObject(i);
                SchoolObject kidslObject = new SchoolObject(jsonObject1.getString("Id").toString(),jsonObject1.getString("Title").toString(),jsonObject1.getString("IsSchool").toString(),jsonObject1.getString("Rate").toString(),jsonObject1.getString("Priority").toString(),jsonObject1.getString("Lat").toString(),jsonObject1.getString("Long").toString() ,jsonObject1.getString("CategoryTitle").toString(),jsonObject1.getString("SubCategoryTitle").toString());
                schoolList.add(kidslObject);
            }

            displaySchools();


        } catch (JSONException e)
        {
            Toast.makeText(getApplicationContext() ,"SOMETHING GO WRONG PLEASE TRY AGAIN ",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private void displaySchools()
    {
        LatLng schoolLatLang ;
        MarkerOptions schoolMarkerOptions ;

        mMap.clear();


        for (int i = 0 ; i < schoolList.size();i++)
        {
            if(schoolList.get(i).getIsSchool().equals("True"))
            {
                schoolLatLang = new LatLng(Double.valueOf(schoolList.get(i).getLat()), Double.valueOf(schoolList.get(i).getLong()));
                schoolMarkerOptions = new MarkerOptions().position(schoolLatLang).icon(BitmapDescriptorFactory.fromResource(R.mipmap.school)).title(schoolList.get(i).getTitle());
                schoolList.get(i).setMarkerId(mMap.addMarker(schoolMarkerOptions).getId());
            }
            else
            {
                schoolLatLang = new LatLng(Double.valueOf(schoolList.get(i).getLat()), Double.valueOf(schoolList.get(i).getLong()));
                schoolMarkerOptions = new MarkerOptions().position(schoolLatLang).icon(BitmapDescriptorFactory.fromResource(R.mipmap.kids)).title(schoolList.get(i).getTitle());
                schoolList.get(i).setMarkerId(mMap.addMarker(schoolMarkerOptions).getId());
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mLocation = new Location( "fff");
        if(requestCode == 5)
        {
            try
            {
                String res = data.getStringExtra("result");
                Map<String, String> result = new HashMap<>() ;
                result.put("res" , res.toString());
                parceData(result);

            }catch (Exception e){}

        }
    }
}

package com.test.weather;

import static com.test.weather.common.isNetworkAvailable;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.gson.Gson;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.test.weather.databinding.ActivityMainBinding;
import com.test.weather.model.WeatherX;
import com.test.weather.model.rest;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.time.DateTimeException;

import com.test.weather.widget.weatherListAdapter;


public class MainActivity extends AppCompatActivity implements LocationListener {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private double longitude = 0.0, latitude = 0.0;
    private LocationManager lm;
    private ProgressDialog progress;

    //variable for test if first  Run app in lifecycle
    private boolean firstRun = true;

    //when get first time location set false for another reload location
    private boolean loc = true;
    //variable for storage all weather items
    public WeatherX m_WeatherX;

    ListView list = null ;
    TextView txtCityName = null;
    TextView txtDesc = null;
    TextView txtMax = null ;
    TextView txtMin = null ;
    TextView txtDt = null ;
    ImageView imgWeather = null ;

    //init components
    private void initComponents(){
        txtCityName = findViewById(R.id.cityName);
        txtDesc = findViewById(R.id.textDesc);
        txtMax = findViewById(R.id.textMax);
        txtMin= findViewById(R.id.textMin);
        txtDt = findViewById(R.id.textdt);
        imgWeather = findViewById(R.id.imageState);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        //StrictMode policy applied to a certain thread
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        progress = new ProgressDialog(this);

        initComponents();

        //get location service
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            //update location evry 1 sec , but in our case updale location after 1 sec and stop update , then call update when require
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MainActivity.this);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                //get location and display inside components view
                getWeather();
            }
        }catch(Exception ex){}



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //get location and display inside components view
                    getWeather();
                }
                catch(Exception ex){}

            }
        });

    }

    void getWeather(){
        // test internet connection
        if (isNetworkAvailable(MainActivity.this)) {
            try {

                // test and grant permmisions for ACCESS GPS
                statusCheck();

                Log.d("lat:", String.valueOf(latitude));


                //call static func to call weather api from web service
                String weatherOBJ = rest.getJSONObjectFromURL("https://api.weatherbit.io/v2.0/forecast/daily?lat="+String.valueOf(latitude)+"&lon="+String.valueOf(longitude)+"&key=ecfb2d52b37948aeb86f300ca38e4d24");
                //json parser object
                Gson gson = new Gson();
                m_WeatherX = gson.fromJson(weatherOBJ, WeatherX.class);

                Log.e("testx", m_WeatherX.getData()[0].getWeather().getIcon());




                try {
                    //init and display weather details
                    initComponents();
                    txtCityName.setText(m_WeatherX.getCity_name());
                    txtDesc.setText(m_WeatherX.getData()[0].getWeather().getDescription());
                    txtMax.setText(String.valueOf(m_WeatherX.getData()[0].getMax_temp()));
                    txtMin.setText(String.valueOf(m_WeatherX.getData()[0].getMin_temp()));
                    txtDt.setText(String.valueOf(m_WeatherX.getData()[0].getDatetime()));

                    Context c = getApplicationContext();
                    int id = c.getResources().getIdentifier("drawable/" + m_WeatherX.getData()[0].getWeather().getIcon(), null, c.getPackageName());

                    imgWeather.setImageResource(id);
                }catch(Exception ex){}

                try {
                    //init weather history components for 15 days and display in list view
                    String cityName = "";
                    String[] desc = new String[15];
                    float[] mMax = new float[15];
                    float[] mMin = new float[15];
                    String[] dt = new String[15];
                    String[] imgid = new String[15];


                    cityName = m_WeatherX.getCity_name();

                    for (int i = 1; i <= 15; i++) {
                        mMax[i - 1] = m_WeatherX.getData()[i].getMax_temp();
                        mMin[i - 1] = m_WeatherX.getData()[i].getMin_temp();
                        dt[i - 1] = m_WeatherX.getData()[i].getDatetime();
                        desc[i - 1] = m_WeatherX.getData()[i].getWeather().getDescription();
                        imgid[i - 1] = m_WeatherX.getData()[i].getWeather().getIcon();
                    }

                    Log.e("testx", desc[0]);


                    weatherListAdapter adapter = new weatherListAdapter(this, cityName, desc, mMax,
                            mMin, dt, imgid);
                    list = (ListView) findViewById(R.id.listWeather);
                    list.setDividerHeight( 2 );
                    list.setAdapter(adapter);

                }catch(Exception ex){}

            } catch(IOException e){
                e.printStackTrace();
            }
        }else{
             Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    //test premmisions
    public void getGPSSatet(){
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        } else {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {

                if(loc == false) {
                    progress.setTitle("Loading");
                    progress.setMessage("Wait while loading Location ..");
                    progress.setCancelable(false);
                    progress.show();
                }
                longitude = location.getLongitude();
                latitude = location.getLatitude();

                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MainActivity.this);

            } else {
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading Location ..");
                progress.setCancelable(false);
                progress.show();

                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MainActivity.this);
            }
        }

    }

    //test if GPS  Enabled and if not go to enable GPS
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }else{
            getGPSSatet();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onResume(){
            // check premmision and get location to display it
            statusCheck();
        super.onResume();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                        progress.setTitle("Loading");
                        progress.setMessage("Wait while loading Location ..");
                        progress.setCancelable(false);
                        progress.show();

                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, MainActivity.this);
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        //variable for storage page => main page for weather today , second page for 15 weather days history
        common.page = 0;

        //thread for get storage location and display it inside components
        //Note : we can use async
        new Thread() {
            public void run() {

                    try {
                        Thread.sleep(300);
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if(m_WeatherX != null){

                                    try {
                                        initComponents();

                                        txtCityName.setText(m_WeatherX.getCity_name());
                                        txtDesc.setText(m_WeatherX.getData()[0].getWeather().getDescription());
                                        txtMax.setText(String.valueOf(m_WeatherX.getData()[0].getMax_temp()));
                                        txtMin.setText(String.valueOf(m_WeatherX.getData()[0].getMin_temp()));
                                        txtDt.setText(String.valueOf(m_WeatherX.getData()[0].getDatetime()));

                                        Context c = getApplicationContext();
                                        int id = c.getResources().getIdentifier("drawable/" + m_WeatherX.getData()[0].getWeather().getIcon(), null, c.getPackageName());
                                        imgWeather.setImageResource(id);
                                    }catch(Exception ex){}


                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            }
        }.start();
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        common.page = 0;
    }

    //call when location is changed or when call update location manually
    @Override
    public void onLocationChanged(@NonNull Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        if(progress!=null)
            if(progress.isShowing())
                progress.dismiss();

            if(firstRun || common.page == 1) {
                getWeather();
                common.page = 0;
                firstRun = false;
            }
        loc = false;
    }
}

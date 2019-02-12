package com.example.jendi.projektgrupowy.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jendi.projektgrupowy.MonumentInfo;
import com.example.jendi.projektgrupowy.R;
import com.example.jendi.projektgrupowy.clients.MonumentClient;
import com.example.jendi.projektgrupowy.models.Monument;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class LoggedActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions addMarker = new MarkerOptions();
    private boolean locationFound = false;
    private LocationManager locationManager;
    private String token;
    private Boolean superUser;
    private List<Monument> monumentList;
    private List<Monument> managedList;
    private MonumentClient monumentClient;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        monumentClient = new MonumentClient();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                .Builder()
                .permitAll()
                .build();
        StrictMode.setThreadPolicy(policy);

        gson = new Gson();

        token = getIntent().getStringExtra("token");
        superUser = getIntent().getBooleanExtra("superUser", false);
        if (!superUser) {
            tabLayout.removeTabAt(1);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapLogged);
        mapFragment.getMapAsync(this);

        Button logout = findViewById(R.id.mapsLogoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoggedActivity.this, MapsActivity.class));
            }
        });

        LocationListener locationListener = new MyLocationListener();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

        final FloatingActionButton add = findViewById(R.id.buttonAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoggedActivity.this, FormActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("position", addMarker.getPosition());
                intent.putExtra("bundle", bundle);
                intent.putExtra("token", token);
                intent.putExtra("mode", "Add");
                startActivity(intent);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        setOnClickListenerForMap();
                        addPinsToMap();
                        add.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        unsetOnClickListenerForMap();
                        addUnapprovedPinsToMap();
                        add.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        mMap.clear();
                        break;
                    case 1:
                        mMap.clear();
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
            }
        });
    }

    private void addPinsToMap()  {
        try {
            monumentList = monumentClient.getAllApprovedMonuments();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        LatLng latLng;
        MonumentInfo monumentInfo;
        for (Monument monument : monumentList) {
            latLng = new LatLng(monument.getCoordinates().getLatitude(), monument.getCoordinates().getLongitude());
            monumentInfo = new MonumentInfo();
            monument.setId(monument.getId());
            monumentInfo.setName(monument.getName());
            monumentInfo.setFunction(monument.getFunction());
            monumentInfo.setCreationDate(monument.getCreationDate().toString());
            monumentInfo.setArchivalSource(monument.getArchivalSource());
            monumentInfo.setStreet(monument.getAddress().getStreet());
            monumentInfo.setHouseNumber(monument.getAddress().getHouseNumber());
            monumentInfo.setFlatNumber(monument.getAddress().getFlatNumber());
            monumentInfo.setPostCode(monument.getAddress().getPostCode());
            monumentInfo.setCity(monument.getAddress().getCity());
            monumentInfo.setCountry(monument.getAddress().getCountry());
            monumentInfo.setId(monument.getId());
            String markerInfoString  = gson.toJson(monumentInfo);

            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(monument.getName())
                    .snippet(markerInfoString));
        }
    }

    private void addUnapprovedPinsToMap() {
        try {
            managedList = monumentClient.getAllUnapprovedMonuments();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMap.setInfoWindowAdapter(null);
        Gson gson = new Gson();
        LatLng latLng;
        MonumentInfo monumentInfo;
        for (Monument monument : managedList) {
            latLng = new LatLng(monument.getCoordinates().getLatitude(), monument.getCoordinates().getLongitude());
            monumentInfo = new MonumentInfo();
            monument.setId(monument.getId());
            monumentInfo.setName(monument.getName());
            monumentInfo.setFunction(monument.getFunction());
            monumentInfo.setCreationDate(monument.getCreationDate().toString());
            monumentInfo.setArchivalSource(monument.getArchivalSource());
            monumentInfo.setStreet(monument.getAddress().getStreet());
            monumentInfo.setHouseNumber(monument.getAddress().getHouseNumber());
            monumentInfo.setFlatNumber(monument.getAddress().getFlatNumber());
            monumentInfo.setPostCode(monument.getAddress().getPostCode());
            monumentInfo.setCity(monument.getAddress().getCity());
            monumentInfo.setCountry(monument.getAddress().getCountry());
            monumentInfo.setId(monument.getId());
            String markerInfoString  = gson.toJson(monumentInfo);

            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(monument.getName())
                    .snippet(markerInfoString));
        }
    }

    private void setOnClickListenerForMap(){
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                addMarker.position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMap.addMarker(addMarker);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addMarker.getPosition()));
                mMap.animateCamera(CameraUpdateFactory.zoomTo( 10.0f ));
                addPinsToMap();
            }
        });
    }

    private void unsetOnClickListenerForMap() {
        mMap.setOnMapClickListener(null);
        mMap.clear();
        addUnapprovedPinsToMap();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(54.3, 18.5)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(7.0f));
        setOnClickListenerForMap();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(LoggedActivity.this, FormActivity.class);
                intent.putExtra("token", token);
                intent.putExtra("mode", "Manage");
                intent.putExtra("marker", marker.getSnippet());
                startActivity(intent);
                return false;
            }
        });
        addPinsToMap();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                else {
                    Toast.makeText(this, "Nie udzielono zgody na użycie lokalizacji.", Toast.LENGTH_LONG).show();;
                }
            }
        }
    }

    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if ((location.getAccuracy() < 5) && !locationFound) {
                LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                addMarker.position(position)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                mMap.addMarker(addMarker);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(addMarker.getPosition()));
                mMap.animateCamera(CameraUpdateFactory.zoomTo( 10.0f ));
                locationFound = true;
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}

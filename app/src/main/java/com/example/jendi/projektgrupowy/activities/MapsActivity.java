package com.example.jendi.projektgrupowy.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jendi.projektgrupowy.MonumentInfo;
import com.example.jendi.projektgrupowy.MonumentInfoWindow;
import com.example.jendi.projektgrupowy.R;
import com.example.jendi.projektgrupowy.clients.MonumentClient;
import com.example.jendi.projektgrupowy.models.Monument;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean locationFound = false;
    private LocationManager locationManager;
    private List<Monument> monumentList;
    private MonumentClient monumentClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy
                                                        .Builder()
                                                        .permitAll()
                                                        .build();
        StrictMode.setThreadPolicy(policy);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationListener locationListener = new MyLocationListener();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                2000,
                10,
                locationListener
        );

        monumentClient = new MonumentClient();

        Button login = findViewById(R.id.mapsLoginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
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


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            monumentList = monumentClient.getAllMonuments();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MonumentInfoWindow infoWindow = new MonumentInfoWindow(MapsActivity.this);
        mMap.setInfoWindowAdapter(infoWindow);
        refreshPins();
    }

    public void refreshPins() {
        for (Monument monument : monumentList) {
            LatLng latLng = new LatLng(monument.getCoordinates().getLatitude(), monument.getCoordinates().getLongitude());
            MonumentInfo monumentInfo = new MonumentInfo();
            monumentInfo.setName(monument.getName());
            monumentInfo.setFunction(monument.getFunction());
            monumentInfo.setCreationDate(monument.getCreationDate());
            monumentInfo.setArchivalSource(monument.getArchivalSource());
            monumentInfo.setStreet(monument.getAddress().getStreet());
            monumentInfo.setHouseNumber(monument.getAddress().getHouseNumber());
            monumentInfo.setFlatNumber(monument.getAddress().getFlatNumber());
            monumentInfo.setPostCode(monument.getAddress().getPostCode());
            monumentInfo.setCity(monument.getAddress().getCity());
            monumentInfo.setCountry(monument.getAddress().getCountry());
            Gson gson = new Gson();
            String markerInfoString  = gson.toJson(monumentInfo);

            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(monument.getName())
                    .snippet(markerInfoString));
        }
    }

    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if ((location.getAccuracy() < 5) && !locationFound) {
                LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(position)
                        .title("Jesteś w tym miejscu")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
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

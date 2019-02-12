package com.example.jendi.projektgrupowy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jendi.projektgrupowy.MonumentInfo;
import com.example.jendi.projektgrupowy.R;
import com.example.jendi.projektgrupowy.clients.MonumentClient;
import com.example.jendi.projektgrupowy.models.AddressRequest;
import com.example.jendi.projektgrupowy.models.CoordinatesRequest;
import com.example.jendi.projektgrupowy.models.Monument;
import com.example.jendi.projektgrupowy.models.MonumentRequest;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.IOException;

public class FormActivity extends AppCompatActivity {
    private MonumentClient monumentClient;
    private LatLng position;
    private MonumentInfo marker;
    private String token;
    private EditText name;
    private EditText function;
    private EditText street;
    private EditText houseNumber;
    private EditText flatNumber;
    private EditText postCode;
    private EditText city;
    private EditText country;
    private EditText creationDate;
    private EditText archive;
    private EditText legalStatus;
    private EditText type;
    private TextView nameT;
    private TextView functionT;
    private TextView streetT;
    private TextView houseNumberT;
    private TextView flatNumberT;
    private TextView postCodeT;
    private TextView cityT;
    private TextView countryT;
    private TextView creationDateT;
    private TextView archiveT;
    private TextView legalStatusT;
    private TextView typeT;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        gson = new Gson();
        if (intent.getStringExtra("mode").equals("Add")) {
            setContentView(R.layout.activity_form);
            Bundle bundle = getIntent().getBundleExtra("bundle");
            position = bundle.getParcelable("position");
        }
        else {
            setContentView(R.layout.activity_manage);
            String tmp = getIntent().getStringExtra("marker");
            marker = gson.fromJson(tmp, MonumentInfo.class);
            nameT = findViewById(R.id.textName);
            functionT = findViewById(R.id.textFunction);
            streetT = findViewById(R.id.textStreet);
            houseNumberT = findViewById(R.id.textHouseNumber);
            flatNumberT = findViewById(R.id.textFlatNumber);
            postCodeT = findViewById(R.id.textPostCode);
            cityT = findViewById(R.id.textCity);
            countryT = findViewById(R.id.textCountry);
            creationDateT = findViewById(R.id.textCreationDate);
            archiveT = findViewById(R.id.textArchive);
            legalStatusT = findViewById(R.id.textLegalStatus);
            typeT = findViewById(R.id.textType);

            nameT.setText(marker.getName());
            functionT.setText(marker.getFunction());
            streetT.setText(marker.getStreet());
            houseNumberT.setText(marker.getHouseNumber());
            flatNumberT.setText(marker.getFlatNumber());
            postCodeT.setText(marker.getPostCode());
            cityT.setText(marker.getCity());
            countryT.setText(marker.getCountry());
            creationDateT.setText(marker.getCreationDate());
            archiveT.setText(marker.getArchivalSource());
            legalStatusT.setText(marker.getLegalStatus());
            typeT.setText(marker.getType());
        }
        monumentClient = new MonumentClient();
        token = getIntent().getStringExtra("token");

        name = findViewById(R.id.editName);
        function = findViewById(R.id.editFunction);
        street = findViewById(R.id.editStreet);
        houseNumber = findViewById(R.id.editHouseNumber);
        flatNumber = findViewById(R.id.editFlatNumber);
        postCode = findViewById(R.id.editPostCode);
        city = findViewById(R.id.editCity);
        country = findViewById(R.id.editCountry);
        creationDate = findViewById(R.id.editCreationDate);
        archive = findViewById(R.id.editArchive);
        legalStatus = findViewById(R.id.editLegalStatus);
        type = findViewById(R.id.editType);

        if (getIntent().getStringExtra("mode").equals("Add")) {
            Button send = findViewById(R.id.buttonSend);
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CoordinatesRequest coordinates = new CoordinatesRequest();
                    coordinates.setLatitude(position.latitude);
                    coordinates.setLongitude(position.longitude);

                    AddressRequest address = new AddressRequest();
                    address.setStreet(street.getText().toString());
                    address.setHouseNumber(houseNumber.getText().toString());
                    address.setFlatNumber(flatNumber.getText().toString());
                    address.setPostCode(postCode.getText().toString());
                    address.setCity(city.getText().toString());
                    address.setCountry(country.getText().toString());

                    MonumentRequest request = new MonumentRequest();
                    request.setName(name.getText().toString());
                    request.setFunction(function.getText().toString());
                    request.setCreationDate(creationDate.getText().toString());
                    request.setArchivalSource(archive.getText().toString());
                    request.setAddress(address);
                    request.setCoordinates(coordinates);
                    request.setStatus(legalStatus.getText().toString());
                    request.setType(type.getText().toString());

                    Monument monument;
                    try {
                        monument = monumentClient.addMonument(token, request);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int a = 17;
                    startActivity(new Intent(FormActivity.this, LoggedActivity.class));
                }
            });
        } else {
            Button accept = findViewById(R.id.buttonAccept);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MonumentClient monumentClient = new MonumentClient();
                    try {
                        monumentClient.approveMonument(token, marker.getId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(FormActivity.this, LoggedActivity.class));
                }
            });
        }
    }
}

package com.example.jendi.projektgrupowy;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

public class MonumentInfoWindow implements GoogleMap.InfoWindowAdapter {

    private Activity context;

    public MonumentInfoWindow(Activity context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = context.getLayoutInflater().inflate(R.layout.monument_info_window, null);
        Gson gson = new Gson();
        MonumentInfo monumentInfo = gson.fromJson(marker.getSnippet(), MonumentInfo.class);

        TextView name = view.findViewById(R.id.name);
        TextView function = view.findViewById(R.id.function);
        TextView creationDate = view.findViewById(R.id.creation_date);
        TextView archivalSource = view.findViewById(R.id.archival_source);
        TextView street = view.findViewById(R.id.street);
        TextView houseNumber = view.findViewById(R.id.house_number);
        TextView flatNumber = view.findViewById(R.id.flat_number);
        TextView postCode = view.findViewById(R.id.post_code);
        TextView city = view.findViewById(R.id.city);
        TextView country = view.findViewById(R.id.country);

        name.setText(monumentInfo.getName());
        function.setText(monumentInfo.getFunction());
        creationDate.setText(monumentInfo.getCreationDate());
        archivalSource.setText(monumentInfo.getArchivalSource());
        street.setText(monumentInfo.getStreet());
        houseNumber.setText(monumentInfo.getHouseNumber());
        flatNumber.setText(monumentInfo.getFlatNumber());
        postCode.setText(monumentInfo.getPostCode());
        city.setText(monumentInfo.getCity());
        country.setText(monumentInfo.getCountry());

        return view;
    }
}

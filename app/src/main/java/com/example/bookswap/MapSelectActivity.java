package com.example.bookswap;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapSelectActivity extends FragmentActivity implements OnMapReadyCallback {

    private LatLng point = new LatLng(53.5,-113.5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_select);

        SupportMapFragment myMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.selectMap);
        myMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map){
        final Marker meetup = map.addMarker(new MarkerOptions()
        .position(point)
        .title("Place Me at meetup point!")
        .draggable(true));
        map.moveCamera(CameraUpdateFactory.zoomTo(15));
        map.moveCamera(CameraUpdateFactory.newLatLng(point));

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                meetup.setPosition(latLng);
            }
        });
    }
}

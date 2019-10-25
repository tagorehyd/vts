package com.ravin.vehicletrackingsystem;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class Transmit extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    DatabaseReference databaseReference;
    LocationManager locationManager;
    LocationListener locationListener;
    Marker marker;



    public Transmit() throws IOException {
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_transmit);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        MapFragment mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync (this);
        Intent intent=getIntent();
        databaseReference= FirebaseDatabase.getInstance ().getReference ("UserDetails").
                child(intent.getExtras().getString("route"));

        locationManager = (LocationManager) getApplicationContext ().getSystemService (LOCATION_SERVICE);
        locationListener = new LocationListener () {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude ();
                double longitude = location.getLongitude ();
                Data d=new Data(latitude,longitude);
                databaseReference.setValue(d);
                LatLng latLng = new LatLng (latitude, longitude);
                if (marker != null) {
                    marker.remove ();
                    marker = mMap.addMarker (new MarkerOptions ().position (latLng).title ("Current"));
                    mMap.setMaxZoomPreference (20);
                    mMap.moveCamera (CameraUpdateFactory.newLatLngZoom (latLng, 12.0f));
                } else {
                    marker = mMap.addMarker (new MarkerOptions ().position (latLng).title ("Current"));
                    mMap.setMaxZoomPreference (20);
                    mMap.moveCamera (CameraUpdateFactory.newLatLngZoom (latLng, 21.0f));
                }

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }

        };
        if (checkSelfPermission (Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission (Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText (getApplicationContext (),"ERROR",Toast.LENGTH_LONG).show();
            return;
        }
        locationManager.requestLocationUpdates (LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates (LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        Toast.makeText (getApplicationContext (),"Transmitting Driver Location",Toast.LENGTH_LONG).show();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }



}

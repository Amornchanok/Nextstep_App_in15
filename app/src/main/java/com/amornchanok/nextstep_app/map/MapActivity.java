package com.amornchanok.nextstep_app.map;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amornchanok.nextstep_app.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private Location mLastKnownLocation;

    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    private TextView tvMapAddress,tvMapConfirm;
    private EditText edtSearch;

    private LatLng nowLatlng;
    private String nowAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initViewData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String apiKey = getString(R.string.google_maps_key);
        Places.initialize(getApplicationContext(), apiKey);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

    }
    private void initViewData(){
        tvMapAddress = findViewById(R.id.tvMapAddress);
        tvMapConfirm = findViewById(R.id.tvMapConfirm);
        edtSearch = findViewById(R.id.edtSearch);

        tvMapConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("lat",nowLatlng.latitude);
                intent.putExtra("lng",nowLatlng.longitude);
                intent.putExtra("address",nowAddress);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        edtSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (edtSearch.getRight() - edtSearch.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        LatLng latLng = getAddressLocation(edtSearch.getText().toString());
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        tvMapAddress.setText(getAddress(latLng));
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_geolocate:
                pickCurrentPlace();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void getLocationPermission() {
        mLocationPermissionGranted = false;
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        mMap.clear();
                        // Add a marker in Sydney and move the camera
                        LatLng cuurent;

                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            cuurent =  new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());
                        } else {
                            cuurent = mDefaultLocation;
                        }
                        tvMapAddress.setText(getAddress(cuurent));
                        mMap.addMarker(new MarkerOptions().position(cuurent).title("cuurent").draggable(true));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cuurent, DEFAULT_ZOOM));

                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void pickCurrentPlace() {
        if (mMap == null) {
            return;
        }

        if (mLocationPermissionGranted) {
            getDeviceLocation();
        } else {
            mMap.addMarker(new MarkerOptions()
                    .title(getString(R.string.default_info_title))
                    .position(mDefaultLocation)
                    .snippet(getString(R.string.default_info_snippet))
                    .draggable(true)
            );

            getLocationPermission();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerDragListener(this);
        getLocationPermission();
        pickCurrentPlace();
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
        tvMapAddress.setText(getAddress(marker.getPosition()));
    }

    private String getAddress(LatLng latLng){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses  = null;
        String addressFull = "";
        try {
            addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude, 1);
            if(addresses.size() != 0){
                addressFull = addresses.get(0).getAddressLine(0);
                nowAddress = addressFull;
                nowLatlng = latLng;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressFull;
    }

    private LatLng getAddressLocation(String search){
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses  = null;
        LatLng latLng = new LatLng(0,0);
        try {
            addresses = geocoder.getFromLocationName(search,5);
            if(addresses.size() > 0){
                latLng = new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
                nowAddress = addresses.get(0).getAddressLine(0);
                nowLatlng = latLng;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return latLng;
    }
}
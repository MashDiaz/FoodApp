package com.example.foodapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng selectedLatLng;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String TAG = "MapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Check for runtime permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            initMap();
        }

        Button btnConfirmLocation = findViewById(R.id.btnConfirmLocation);
        if (btnConfirmLocation == null) {
            Log.e(TAG, "Error: btnConfirmLocation is null. Check your layout file.");
        }

        btnConfirmLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedLatLng != null) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("latitude", selectedLatLng.latitude);
                    returnIntent.putExtra("longitude", selectedLatLng.longitude);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                } else {
                    Toast.makeText(MapActivity.this, "Please select a location.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment == null) {
            Log.e(TAG, "Error: Map fragment is null. Check your layout file.");
            return;
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initMap();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "Map is ready");
        mMap = googleMap;

        if (mMap == null) {
            Log.e(TAG, "Error: GoogleMap object is null");
            return;
        }

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (latLng == null) {
                    Log.e(TAG, "Error: LatLng object is null");
                    return;
                }
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng));
                selectedLatLng = latLng;
            }
        });
    }
}

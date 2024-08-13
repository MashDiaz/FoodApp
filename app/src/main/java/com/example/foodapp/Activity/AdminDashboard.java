package com.example.foodapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodapp.R;
import com.google.android.gms.maps.model.LatLng;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.passAdminLoginpass), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle adding items
        Button btnadminAddItems = findViewById(R.id.btnadminAddItems);
        btnadminAddItems.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, AdminAddItemsSec.class);
            startActivity(intent);
        });

        // Handle adding location
        Button btnAddLocation = findViewById(R.id.btnAddLocation);
        btnAddLocation.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, MapActivity.class);  // Assuming you have a MapActivity for location picking
            startActivityForResult(intent, 200);  // Using a request code of 200
        });

        // Handle managing users
        Button btnMngUsers = findViewById(R.id.btnMngUsers);
        btnMngUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDashboard.this, ManageUsersActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200 && resultCode == RESULT_OK) {
            double latitude = data.getDoubleExtra("latitude", 0.0);
            double longitude = data.getDoubleExtra("longitude", 0.0);

            // Optionally, save the location to SQLite or display a message
            saveLocationToDatabase(new LatLng(latitude, longitude), "Selected Location");
            Toast.makeText(this, "Location saved successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    // Add the method to save location to SQLite
    private void saveLocationToDatabase(LatLng latLng, String placeName) {
        // Your SQLite saving logic here
    }
}

package com.example.foodapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.Activity.AdminAddItemsSec;
import com.example.foodapp.Activity.ManageUsersActivity;
import com.example.foodapp.Activity.MapActivity;
import com.example.foodapp.R;

public class AdminDashboard extends AppCompatActivity {
    private DBHelper dbHelper ;
    private TextView txtTotalOrderCount;
    private TextView txtRevGenTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        ImageButton btnadminAddItems = findViewById(R.id.btnadminAddItems);
        ImageButton btnAddLocation = findViewById(R.id.btnAddLocation);
        ImageButton btnMngUsers = findViewById(R.id.btnMngUsers);
        ImageButton btnDiscountConfig = findViewById(R.id.btnDiscountConfig);
        dbHelper = new DBHelper(this);
        txtTotalOrderCount = findViewById(R.id.txtTotalOrderCount);
        txtRevGenTotal = findViewById(R.id.txtRevGenTotal);
        int totalOrders = dbHelper.getTotalOrders();
        double totalRevenue = dbHelper.getTotalRevenue();

        // Update TextViews
        txtTotalOrderCount.setText(String.valueOf(totalOrders));
        txtRevGenTotal.setText(String.format("$%.2f", totalRevenue));

        btnadminAddItems.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, AdminAddItemsSec.class);
            startActivity(intent);
        });

        btnAddLocation.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, MapActivity.class);
            startActivityForResult(intent, 200);
        });

        btnMngUsers.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboard.this, ManageUsersActivity.class);
            startActivity(intent);
        });

//        btnDiscountConfig.setOnClickListener(v -> {
//            Intent intent = new Intent(AdminDashboard.this, DiscountConfigActivity.class);
//            startActivity(intent);
//        });
    }
}

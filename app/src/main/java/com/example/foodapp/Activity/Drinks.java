package com.example.foodapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.ItemAdapter;
import com.example.foodapp.R;

import java.util.List;

public class Drinks extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);

        recyclerView = findViewById(R.id.drinksRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DBHelper dbHelper = new DBHelper(this);
        itemList = dbHelper.getAllDrinks();

        itemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(itemAdapter);

        // Initialize ImageViews and set click listeners
        ImageView homeImageView = findViewById(R.id.imageView6);
        ImageView foodImageView = findViewById(R.id.imageView19);
        ImageView drinksImageView = findViewById(R.id.imageView20);
        ImageView accountImageView = findViewById(R.id.imageView22);
        ImageView cartImageView = findViewById(R.id.imageView23);

        homeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Home Activity
                Log.d("DrinksActivity", "Home button clicked");
                Intent intent = new Intent(Drinks.this, Dashboard.class);
                startActivity(intent);
            }
        });

        foodImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Food Activity
                Intent intent = new Intent(Drinks.this, Foods.class);
                startActivity(intent);
            }
        });

        drinksImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Drinks Activity (this activity itself, or you can open another activity)
                Intent intent = new Intent(Drinks.this, Drinks.class);
                startActivity(intent);
            }
        });

        accountImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Account Activity
                Intent intent = new Intent(Drinks.this, UserAccount.class);
                startActivity(intent);
            }
        });

        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Cart Activity
                Intent intent = new Intent(Drinks.this, Cart.class);
                startActivity(intent);
            }
        });
    }
}

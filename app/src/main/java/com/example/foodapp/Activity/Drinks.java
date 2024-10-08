package com.example.foodapp.Activity;

import static com.example.foodapp.R.id.button;
import static com.example.foodapp.R.layout.activity_drinks;
import static com.example.foodapp.R.layout.activity_foods;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.ItemAdapter;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;
public class Drinks extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_drinks);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        DBHelper dbHelper = new DBHelper(this);
        itemList = dbHelper.getAllDrinks();
        System.out.println(itemList);

        itemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(itemAdapter);

        Button cartButton = findViewById(button);
        cartButton.setOnClickListener(v -> addToCart());
    }

    public void addToCart() {
        ArrayList<String> itemNames = itemAdapter.getItemNames();
        ArrayList<String> itemPrices = itemAdapter.getItemPrices();

        // Intent to move to the cart activity
        Intent intent = new Intent(Drinks.this, Cart.class);
        intent.putStringArrayListExtra("cartNames", itemNames);
        intent.putStringArrayListExtra("cartPrices", itemPrices);
        startActivity(intent);

    }


}
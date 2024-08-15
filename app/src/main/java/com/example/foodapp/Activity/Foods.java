package com.example.foodapp.Activity;


import static com.example.foodapp.R.layout.activity_foods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import static com.example.foodapp.R.id.button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.ItemAdapter;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class Foods extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_foods);
        recyclerView = findViewById(R.id.recyclerView);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

       //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DBHelper dbHelper = new DBHelper(this);
        itemList = dbHelper.getAllFood();
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
        Intent intent = new Intent(Foods.this, Cart.class);
        intent.putStringArrayListExtra("cartNames", itemNames);
        intent.putStringArrayListExtra("cartPrices", itemPrices);
        startActivity(intent);

    }

    public void displayFood(View view)
    {
        Intent startIntent = new Intent(this , Foods.class);
        startActivity(startIntent);
    }
    public void displayDrinks(View view)
    {
        Intent startIntent = new Intent(this , Drinks.class);
        startActivity(startIntent);
    }
    public void displayAccount(View view)
    {
        Intent startIntent = new Intent(this , Dashboard.class);
        startActivity(startIntent);
    }
    public void displayHome(View view)
    {
        Intent startIntent = new Intent(this , Dashboard.class);
        startActivity(startIntent);
    }

    public void displayCart(View view)
    {
        Intent startIntent = new Intent(this , Cart.class);
        startActivity(startIntent);
    }



}

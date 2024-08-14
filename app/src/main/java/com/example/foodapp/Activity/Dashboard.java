package com.example.foodapp.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.CategoryAdapter;
import com.example.foodapp.Domain.CategoryDomain;
import com.example.foodapp.R;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {


    private RecyclerView.Adapter catAdapter;
    private RecyclerView recyclerViewCat;

    private void initRecyclerviewCat() {
        ArrayList<CategoryDomain> items = new ArrayList<>();
        items.add(new CategoryDomain("cat1", "Vegetable"));
        items.add(new CategoryDomain("cat2", "Fruits"));
        items.add(new CategoryDomain("cat3", "Dairy"));
        items.add(new CategoryDomain("cat4", "Drinks"));
        items.add(new CategoryDomain("cat5", "Grain"));

        recyclerViewCat = findViewById(R.id.catView);
        recyclerViewCat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        catAdapter = new CategoryAdapter(items);
        recyclerViewCat.setAdapter(catAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

        // Retrieve the logged-in user's name from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String loggedInUser = sharedPreferences.getString("loggedInUser", null);

        // Find the TextView and set the username
        TextView usernameTextView = findViewById(R.id.customerTxt); // Replace with your TextView ID
        if (loggedInUser != null) {
            usernameTextView.setText("Hello, " + getLoggedInUser(loggedInUser));
        }

        // Setting up AutoCompleteTextView for search bar
        String[] foodslist = getResources().getStringArray(R.array.search_items);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.search_bar);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foodslist);
        autoCompleteTextView.setAdapter(adapter);

        // Initialize RecyclerView or any other views
        initRecyclerviewCat();
    }


    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public String getLoggedInUser(String username){
        DBHelper dbHelper = new DBHelper(Dashboard.this);
        String loggedInUser = dbHelper.getLoggedInUser(username);
        return loggedInUser;
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class<?> secondActivity) {
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
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

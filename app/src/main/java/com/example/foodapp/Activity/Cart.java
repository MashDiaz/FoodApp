package com.example.foodapp.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.CartAdapter;
import com.example.foodapp.R;

import java.util.List;

public class Cart extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView totalPriceTextView;
    private TextView priceTextView11;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize RecyclerView and TextView
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        priceTextView11 = findViewById(R.id.textView11); // Find the TextView with ID textView11

        // Retrieve the data passed from the Foods activity
        List<String> cartNames = getIntent().getStringArrayListExtra("cartNames");
        List<String> cartPrices = getIntent().getStringArrayListExtra("cartPrices");

        // Initialize and set up the adapter
        cartAdapter = new CartAdapter(this, cartNames, cartPrices);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        // Calculate and display the total price
        calculateTotal(cartPrices);
    }

    private void calculateTotal(List<String> cartPrices) {
        double total = 0;
        for (String price : cartPrices) {
            total += Double.parseDouble(price); // Use Double.parseDouble to handle decimal values
        }
        String tot = String.valueOf(total);
        priceTextView11.setText("Total: Rs. " + tot); // Set the total value to the TextView
        Toast.makeText(this, "Total: Rs. " + total, Toast.LENGTH_SHORT).show(); // Optional: Show a toast message with the total price
    }
}

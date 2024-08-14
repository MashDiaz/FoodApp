package com.example.foodapp.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.CartAdapter;
import com.example.foodapp.R;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        List<String> cartNames = getIntent().getStringArrayListExtra("cartNames");
        List<String> cartPrices = getIntent().getStringArrayListExtra("cartPrices");

        if (cartNames == null) {
            cartNames = new ArrayList<>();
        }

        if (cartPrices == null) {
            cartPrices = new ArrayList<>();
        }

        cartAdapter = new CartAdapter(this, cartNames, cartPrices);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        calculateTotal(cartPrices);
    }

    private void calculateTotal(List<String> cartPrices) {
        int total = 0;
        for (String price : cartPrices) {
            total += Integer.parseInt(price);
        }
        totalPriceTextView.setText("Total: Rs. " + total);
    }
}

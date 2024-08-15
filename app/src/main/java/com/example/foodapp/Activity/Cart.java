package com.example.foodapp.Activity;

import static com.example.foodapp.R.layout.activity_cart;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

    private TextView priceTextView11;
    private EditText promoEditText;
    private Button checkPromoButton;
    private Button placeOrderButton; // Button6 for placing an order
    private double total;
    private DBHelper dbHelper;
    private String loggedInUser; // This will hold the logged-in user's username

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_cart);

        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        loggedInUser = sharedPreferences.getString("loggedInUser", null);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);


        // Initialize RecyclerView, TextView, EditText, and Button
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        priceTextView11 = findViewById(R.id.textView11);
        promoEditText = findViewById(R.id.textView29);
        checkPromoButton = findViewById(R.id.button7);
        placeOrderButton = findViewById(R.id.button6); // Button6 for placing an order

        // Retrieve the data passed from the Foods activity
        List<String> cartNames = getIntent().getStringArrayListExtra("cartNames");
        List<String> cartPrices = getIntent().getStringArrayListExtra("cartPrices");

        // Initialize and set up the adapter
        cartAdapter = new CartAdapter(this, cartNames, cartPrices);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        // Calculate and display the total price
        calculateTotal(cartPrices);

        // Set up the promo code check
        checkPromoButton.setOnClickListener(v -> {
            String promoCode = promoEditText.getText().toString().trim();
            applyPromoCode(promoCode);
        });

        // Set up the place order button
        placeOrderButton.setOnClickListener(v -> placeOrder(cartNames, cartPrices));
    }

    private void calculateTotal(List<String> cartPrices) {
        total = 0;
        for (String price : cartPrices) {
            total += Double.parseDouble(price);
        }
        displayTotal(total);
    }

    private void displayTotal(double totalAmount) {
        String tot = String.valueOf(totalAmount);
        priceTextView11.setText("Total: Rs. " + tot);
        Toast.makeText(this, "Total: Rs. " + tot, Toast.LENGTH_SHORT).show();
    }

    private void applyPromoCode(String promoCode) {
        double discount = 0;

        switch (promoCode.toUpperCase()) {
            case "FB10":
                discount = 0.10;
                break;
            case "FB20":
                discount = 0.20;
                break;
            case "FB30":
                discount = 0.30;
                break;
            default:
                Toast.makeText(this, "Invalid Promo Code", Toast.LENGTH_SHORT).show();
                return;
        }

        double discountedTotal = total - (total * discount);
        int discountedTot = (int) discountedTotal;
        displayTotal(discountedTot);
    }

    private void placeOrder(List<String> cartNames, List<String> cartPrices) {
        // Get the username from SharedPreferences
        if (loggedInUser != null) {
            // Insert order details into the database
            for (int i = 0; i < cartNames.size(); i++) {
                dbHelper.addOrderDetails(loggedInUser, cartNames.get(i), Double.parseDouble(cartPrices.get(i)));
            }

            // Show a success message
            Toast.makeText(this, "Order placed successfully!", Toast.LENGTH_SHORT).show();

            // Clear the cart items
            cartNames.clear();
            cartPrices.clear();
            cartAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed

            // Reset the total price
            total = 0;
            displayTotal(total);

            // Optionally clear the promo code input
            promoEditText.setText("");
        } else {
            Toast.makeText(this, "Failed to place order. User not found.", Toast.LENGTH_SHORT).show();
        }
    }

}

package com.example.foodapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdminAddItemsSec extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private DBHelper dbHelper;

    private EditText etItemName, etPrice, etCategory, etDescription;
    private CalendarView calendarView;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_items_sec);

        // Initialize UI components
        etItemName = findViewById(R.id.txtAIProname);
        etPrice = findViewById(R.id.txtAIProPrice);
        etCategory = findViewById(R.id.txtAIProCat);
        etDescription = findViewById(R.id.txtAIProDis);
        calendarView = findViewById(R.id.calendarView);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        // Get selected date from CalendarView
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
        });

        // Set default date as today's date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        selectedDate = sdf.format(new Date());

        Button btnSelectImage = findViewById(R.id.btnSelectImage);
        Button addButton = findViewById(R.id.btnAdd);
        btnSelectImage.setOnClickListener(v -> openImageSelector());
        addButton.setOnClickListener(v -> onAddClick());
    }

    private void openImageSelector() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private void onAddClick() {
        // Get data from form fields
        String itemName = etItemName.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String category = etCategory.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        // Validate inputs
        if (itemName.isEmpty() || priceStr.isEmpty() || category.isEmpty() || description.isEmpty() || imageUri == null) {
            Toast.makeText(this, "Please fill in all fields and select an image.", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);

        // Convert image URI to byte array
        byte[] imageInBytes = convertImageToByteArray(imageUri);

        if (imageInBytes != null) {
            // Insert food item into the database
            boolean isInserted = dbHelper.addFood(itemName, price, category, imageInBytes, description, selectedDate);

            if (isInserted) {
                Toast.makeText(this, "Item added successfully.", Toast.LENGTH_SHORT).show();
                // Clear form or navigate away
            } else {
                Toast.makeText(this, "Failed to add item.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Failed to convert image.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Toast.makeText(this, "Image selected", Toast.LENGTH_SHORT).show();
        }
    }

    // Convert image URI to byte array
    private byte[] convertImageToByteArray(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

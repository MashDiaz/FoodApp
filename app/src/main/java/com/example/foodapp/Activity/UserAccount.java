package com.example.foodapp.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;

public class UserAccount extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private ImageView imageViewCurrentProfile;
    private EditText editTextName, editTextEmail;
    private TextView textViewCurrentName, textViewCurrentEmail;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer);

        dbHelper = new DBHelper(this);

        imageViewCurrentProfile = findViewById(R.id.imageViewCurrentProfile);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        textViewCurrentName = findViewById(R.id.textViewCurrentName);
        textViewCurrentEmail = findViewById(R.id.textViewCurrentEmail);

        // Set initial data from the database
        loadUserProfile();

        Button buttonUploadImage = findViewById(R.id.buttonUploadImage);
        Button buttonSaveProfile = findViewById(R.id.buttonSaveProfile);
        Button buttonViewOrderHistory = findViewById(R.id.buttonViewOrderHistory);
        Button buttonViewFavorites = findViewById(R.id.buttonViewFavorites);

        buttonUploadImage.setOnClickListener(v -> openImageSelector());
        buttonSaveProfile.setOnClickListener(v -> saveUserProfile());
        buttonViewOrderHistory.setOnClickListener(v -> viewOrderHistory());
        buttonViewFavorites.setOnClickListener(v -> viewFavorites());
    }

    private void loadUserProfile() {
        // Fetch user details from the database
        Cursor cursor = dbHelper.getUserDetails();
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            // Set data to TextViews and EditTexts
            textViewCurrentName.setText(name);
            textViewCurrentEmail.setText(email);
            editTextName.setText(name);
            editTextEmail.setText(email);
        }
        cursor.close();
    }

    private void openImageSelector() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageViewCurrentProfile.setImageURI(imageUri);
            Toast.makeText(this, "Image selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserProfile() {
        String newName = editTextName.getText().toString();
        String newEmail = editTextEmail.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", newName);
        contentValues.put("email", newEmail);

        boolean isUpdated = dbHelper.updateUserDetails(contentValues);

        if (isUpdated) {
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            loadUserProfile(); // Refresh the profile with updated data
        } else {
            Toast.makeText(this, "Profile update failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void viewOrderHistory() {
        // order history ekadanna
    }

    private void viewFavorites() {
        // fav tika hadanna
    }
}

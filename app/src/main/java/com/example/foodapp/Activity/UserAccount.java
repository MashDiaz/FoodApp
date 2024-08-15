package com.example.foodapp.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
    }

    private void loadUserProfile() {
        // Assuming the logged-in user's username is passed to this activity

        // Fetch user details from the database
        textViewCurrentName.setText("Rakitha ");
        textViewCurrentEmail.setText("tharupathir@gmail.com");
        editTextName.setText("Rakitha");
        editTextEmail.setText("tharupathir@gmail.com");

    }




}

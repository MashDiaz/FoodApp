package com.example.foodapp.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.foodapp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;

    ImageView currentProfileImage;
    TextView currentName, currentEmail;
    EditText editTextName, editTextEmail;
    Button buttonViewOrderHistory, buttonViewFavorites, buttonUploadImage, buttonSaveProfile;

    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer);

        currentProfileImage = findViewById(R.id.imageViewCurrentProfile);
        currentName = findViewById(R.id.textViewCurrentName);
        currentEmail = findViewById(R.id.textViewCurrentEmail);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonViewOrderHistory = findViewById(R.id.buttonViewOrderHistory);
        buttonViewFavorites = findViewById(R.id.buttonViewFavorites);
        buttonUploadImage = findViewById(R.id.buttonUploadImage);
        buttonSaveProfile = findViewById(R.id.buttonSaveProfile);

        // Retrieve user details passed from the SignUp activity
        Intent intent = getIntent();
        String currentUserName = intent.getStringExtra("username");
        String currentUserEmail = intent.getStringExtra("email");

        currentName.setText(currentUserName);
        currentEmail.setText(currentUserEmail);

        buttonSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editTextName.getText().toString();
                String newEmail = editTextEmail.getText().toString();

                if (!newName.isEmpty() && !newEmail.isEmpty()) {
                    currentName.setText(newName);
                    currentEmail.setText(newEmail);
                    Toast.makeText(Customer.this, "Profile updated successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Customer.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonViewOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Customer.this, "Order history clicked", Toast.LENGTH_LONG).show();
            }
        });

        buttonViewFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Customer.this, "Favorites clicked", Toast.LENGTH_LONG).show();
            }
        });

        buttonUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(Customer.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(Customer.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {

                    Log.d("Permissions", "Requesting camera and storage permissions");
                    ActivityCompat.requestPermissions(Customer.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_CAMERA_PERMISSION);
                } else {
                    Log.d("Permissions", "Permissions granted, dispatching intent");
                    dispatchTakePictureIntent();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Camera permission is required to take a photo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e("CameraIntent", "Error occurred while creating the file", ex);
            }
            if (photoFile != null) {
                photoUri = FileProvider.getUriForFile(this,
                        "com.example.foodapp.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } else {
                Toast.makeText(this, "Error creating file for photo", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d("CameraIntent", "No activity can handle the camera intent");
            Toast.makeText(this, "No camera app available to handle the intent", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult", "Request code: " + requestCode + ", Result code: " + resultCode);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                currentProfileImage.setImageBitmap(bitmap);
                Log.d("onActivityResult", "Image captured and set to ImageView");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("onActivityResult", "Failed to load image", e);
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d("onActivityResult", "Image capture canceled or failed");
        }
    }

    private File createImageFile() throws IOException {
        Log.d("ImageFile", "Creating image file");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        return image;
    }

    // Handling button clicks through XML-defined onClick methods
    public void onViewOrderHistoryClick(View view) {
        Toast.makeText(this, "Order history clicked", Toast.LENGTH_SHORT).show();
    }

    public void onViewFavoritesClick(View view) {
        Toast.makeText(this, "Favorites clicked", Toast.LENGTH_SHORT).show();
    }

    public void onUploadImageClick(View view) {
        buttonUploadImage.performClick();
    }

    public void onSaveProfileClick(View view) {
        buttonSaveProfile.performClick();
    }
}

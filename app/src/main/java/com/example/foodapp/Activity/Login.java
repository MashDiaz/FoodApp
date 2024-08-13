package com.example.foodapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodapp.R;


public class Login extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);

    }

    public void goSignUp(View view) {
        Intent startIntent = new Intent(this, SignUp.class);
        startActivity(startIntent);
    }

    public void loginUser(View view) {
        EditText username = findViewById(R.id.emailText);
        EditText password = findViewById(R.id.passwordText);

        String user = username.getText().toString();
        String pwd = password.getText().toString();
        if(user.equals("admin")&&pwd.equals("admin")){
            Toast.makeText(Login.this, "Admin Login Successful", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, AdminDashboard.class);
            startActivity(intent);
        }
        if (user.equals("") || pwd.equals("")) {
            Toast.makeText(Login.this, "Please fill all fields", Toast.LENGTH_LONG).show();
        } else {
            boolean isValid = dbHelper.checkUsernamePassword(user, pwd);
            if (isValid) {
                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                // Proceed to the next activity after successful login
                Intent intent = new Intent(this, Dashboard.class);
                startActivity(intent);
            } else {
                Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
            }
        }
    }
}
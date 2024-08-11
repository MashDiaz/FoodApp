package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        EditText username = findViewById(R.id.editTextText);
        EditText email = findViewById(R.id.editTextText5);
        EditText password = findViewById(R.id.editTextText3);
        EditText repassword = findViewById(R.id.editTextText2);
        Button btnSign = findViewById(R.id.button4);
        dbHelper = new DBHelper(this);

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user, email1, pwd, rePwd;
                user = username.getText().toString();
                email1 = email.getText().toString();
                pwd = password.getText().toString();
                rePwd = repassword.getText().toString();

                if (user.equals("") || pwd.equals("") || rePwd.equals("")) {
                    Toast.makeText(SignUp.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                } else {
                    if (pwd.equals(rePwd)) {
                        if (dbHelper.checkUsername(user)) {
                            Toast.makeText(SignUp.this, "User Already Exists", Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean register = dbHelper.insertDetails(user, email1, pwd);
                        if (register) {
                            Toast.makeText(SignUp.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SignUp.this, "User Registration Failed", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}

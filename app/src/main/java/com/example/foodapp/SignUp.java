package com.example.foodapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;


public class SignUp extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        EditText username =(EditText) findViewById(R.id.txtName);
        EditText email =(EditText) findViewById(R.id.txtEmail);
        EditText password =(EditText) findViewById(R.id.txtPsw);
        EditText repassword =(EditText) findViewById(R.id.txtRePsw);
        Button btnSign =findViewById(R.id.btnSign);
        dbHelper = new DBHelper(this);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user,email1,pwd,rePwd;
                user = username.getText().toString();
                email1 = email.getText().toString();
                pwd = password.getText().toString();
                rePwd = repassword.getText().toString();

                if(user.equals("") || pwd.equals("") || rePwd.equals("")){
                    Toast.makeText(SignUp.this,"Please fill all fields",Toast.LENGTH_LONG).show();

                }
                else
                {
                    if(pwd.equals(rePwd))
                    {
                        if(dbHelper.checkUsername(user)){
                            Toast.makeText(SignUp.this,"User Already Exists",Toast.LENGTH_LONG).show();
                            return;
                        }
                        boolean register = dbHelper.insertDetails(user,email1,pwd);
                        if(register) {
                            Toast.makeText(SignUp.this, "User Registered Successfully", Toast.LENGTH_LONG).show();

                        }
                        else
                            Toast.makeText(SignUp.this,"User Registration Failed",Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        Toast.makeText(SignUp.this,"Password do not match",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

    }

}

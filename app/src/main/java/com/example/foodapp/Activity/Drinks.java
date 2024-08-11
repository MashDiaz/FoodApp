package com.example.foodapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.foodapp.R;

public class Drinks extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout dashboard, foods, drinks, beverages, cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);

        drawerLayout =findViewById(R.id.drawerlayout);
        dashboard=findViewById(R.id.dashboard);
        foods=findViewById(R.id.Food);
        drinks=findViewById(R.id.drinks);
        beverages=findViewById(R.id.beverages);
        cart=findViewById(R.id.cart);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                openDrawer(drawerLayout);
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                redirectActivity(Drinks.this, Dashboard.class);

            }
        });

        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                redirectActivity(Drinks.this, Foods.class);
            }
        });

        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                recreate();
            }
        });

        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                redirectActivity(Drinks.this, Beverages.class);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                redirectActivity(Drinks.this, Cart.class);
            }
        });
        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(Drinks.this, "Logout", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void openDrawer(DrawerLayout drawerLayout)
    {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class secondActivty)
    {
        Intent intent=new Intent(activity,secondActivty);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }


}
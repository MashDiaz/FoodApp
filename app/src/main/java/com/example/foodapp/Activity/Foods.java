package com.example.foodapp.Activity;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapter.ItemAdapter;
import com.example.foodapp.R;

import java.util.List;

public class Foods extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DBHelper dbHelper = new DBHelper(this);
        itemList = dbHelper.getAllItems();
        System.out.println(itemList);

        itemAdapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(itemAdapter);
    }
}

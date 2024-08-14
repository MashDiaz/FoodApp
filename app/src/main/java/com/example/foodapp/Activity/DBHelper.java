package com.example.foodapp.Activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAme ="registerDB";
    SQLiteDatabase db=this.getWritableDatabase();


    public DBHelper(@Nullable Context context){
        super(context,DBNAme,null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key,email TEXT,password TEXT)");

        db.execSQL("CREATE TABLE food(" +
                "food_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "food_name TEXT, " +
                "price REAL, " +
                "category TEXT, " +
                "image BLOB, " +
                "description TEXT, " +
                "date_added TEXT)");

        db.execSQL("CREATE TABLE orders(" +
                "order_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "customer_id TEXT, " +
                "food_ids TEXT, " +
                "order_time TEXT, " +
                "total_price REAL, " +
                "FOREIGN KEY(customer_id) REFERENCES users(username))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(db);

    }

    public boolean insertDetails(String username, String email, String password){

        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result =db.insert("users",null,contentValues);
        if(result== -1)
            return false;
        else
            return true;
    }

    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username = ?",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean checkUsernamePassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean addFood(String foodName, double price, String category, byte[] image, String description, String dateAdded) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("food_name", foodName);
        contentValues.put("price", price);
        contentValues.put("category", category);
        contentValues.put("image", image);
        contentValues.put("description", description);
        contentValues.put("date_added", dateAdded);

        long result = db.insert("food", null, contentValues);
        return result != -1;
    }
    public boolean addOrder(String customerId, String foodIds, String orderTime, double totalPrice) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_id", customerId);
        contentValues.put("food_ids", foodIds);
        contentValues.put("order_time", orderTime);
        contentValues.put("total_price", totalPrice);

        long result = db.insert("orders", null, contentValues);
        return result != -1;
    }

    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM food", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int foodId = cursor.getInt(cursor.getColumnIndex("food_id"));
                @SuppressLint("Range") String foodName = cursor.getString(cursor.getColumnIndex("food_name"));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex("category"));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                itemList.add(new Item(foodId, foodName, price, category, image, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemList;
    }
    public  List<Item>getAllDrinks(){
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM food WHERE category = 'Drink'", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int foodId = cursor.getInt(cursor.getColumnIndex("food_id"));
                @SuppressLint("Range") String foodName = cursor.getString(cursor.getColumnIndex("food_name"));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex("category"));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                itemList.add(new Item(foodId, foodName, price, category, image, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemList;
    }
    public  List<Item>getAllFood(){
        List<Item> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM food WHERE category = 'Food'", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int foodId = cursor.getInt(cursor.getColumnIndex("food_id"));
                @SuppressLint("Range") String foodName = cursor.getString(cursor.getColumnIndex("food_name"));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex("category"));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                itemList.add(new Item(foodId, foodName, price, category, image, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemList;
    }

    public String getLoggedInUser(String usernameOrEmail) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to retrieve the user's name based on the username or email
        Cursor cursor = db.rawQuery("SELECT username FROM users WHERE username = ? OR email = ?", new String[]{usernameOrEmail, usernameOrEmail});

        if (cursor.moveToFirst()) {
            // Retrieve the username from the cursor
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            cursor.close();
            return username;
        } else {
            cursor.close();
            return null; // User not found
        }
    }
}
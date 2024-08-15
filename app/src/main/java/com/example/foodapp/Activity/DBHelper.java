package com.example.foodapp.Activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "registerDB";
    SQLiteDatabase db = this.getWritableDatabase();

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(" +
                "username TEXT PRIMARY KEY, " +
                "email TEXT, " +
                "password TEXT)");

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

        db.execSQL("CREATE TABLE favourites(" +
                "favourite_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id TEXT, " +
                "food_id INTEGER, " +
                "FOREIGN KEY(user_id) REFERENCES users(username), " +
                "FOREIGN KEY(food_id) REFERENCES food(food_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS food");
        db.execSQL("DROP TABLE IF EXISTS orders");
        db.execSQL("DROP TABLE IF EXISTS favourites");
        onCreate(db);
    }

    // Method to update user details in the users table
    public boolean updateUserDetails(ContentValues contentValues) {
        // Get the current username from the ContentValues object
        String username = contentValues.getAsString("username");

        // Update the user details in the users table
        int result = db.update("users", contentValues, "username = ?", new String[]{username});

        return result > 0; // Return true if update was successful, false otherwise
    }

    public boolean addFavourite(String userId, int foodId) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id", userId);
        contentValues.put("food_id", foodId);

        long result = db.insert("favourites", null, contentValues);
        return result != -1;
    }

    public List<Item> getAllFavourites(String userId) {
        List<Item> favouriteItems = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT food.* FROM food " +
                "INNER JOIN favourites ON food.food_id = favourites.food_id " +
                "WHERE favourites.user_id = ?", new String[]{userId});

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int foodId = cursor.getInt(cursor.getColumnIndex("food_id"));
                @SuppressLint("Range") String foodName = cursor.getString(cursor.getColumnIndex("food_name"));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex("category"));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("description"));

                favouriteItems.add(new Item(foodId, foodName, price, category, image, description));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return favouriteItems;
    }

    public boolean insertDetails(String username, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        return result != -1;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
        return cursor.getCount() > 0;
    }

    public boolean checkUsernamePassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
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

    public List<Item> getAllDrinks() {
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

    public List<Item> getAllFood() {
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
        Cursor cursor = db.rawQuery("SELECT username FROM users WHERE username = ? OR email = ?", new String[]{usernameOrEmail, usernameOrEmail});
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            cursor.close();
            return username;
        }
        cursor.close();
        return null;
    }
    public String[] getUserDetails(String username) {
       String[] userDetails = new String[2];
       return  userDetails;

    }


}

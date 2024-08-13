package com.example.foodapp.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAme ="registerDB";
    SQLiteDatabase db=this.getWritableDatabase();


    public DBHelper(@Nullable Context context){
        super(context,DBNAme,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key,email TEXT,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");

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
}

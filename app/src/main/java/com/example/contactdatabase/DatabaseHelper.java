package com.example.contactdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "USER_Manager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "USER";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE_OF_BIRTH = "dateOfBirth";
    private static final String KEY_EMAIL= "email";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_USER_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, KEY_ID, KEY_NAME, KEY_DATE_OF_BIRTH, KEY_EMAIL);
        db.execSQL(create_USER_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_USER_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_USER_table);

        onCreate(db);
    }

    public long insertData(String name, String dateOfBirth, String email)
    {
        ContentValues rowValue = new ContentValues();
        rowValue.put(KEY_NAME,name);
        rowValue.put(KEY_DATE_OF_BIRTH ,dateOfBirth);
        rowValue.put(KEY_EMAIL,email);

        Log.d("table: " ,rowValue.toString());
        return db.insertOrThrow(TABLE_NAME, null, rowValue);
    }
    public List<UserModel> getData(){
        String[] projection = {
                KEY_ID,
                DatabaseHelper.KEY_NAME,
                DatabaseHelper.KEY_DATE_OF_BIRTH,
                DatabaseHelper.KEY_EMAIL,
        };

// Filter results WHERE "title" = 'My Title'
        String selection = DatabaseHelper.KEY_ID + " = ?";
        //String[] selectionArgs = { "My Title" };

// How you want the results sorted in the resulting Cursor
//        String sortOrder =

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        cursor.moveToFirst();
        List<UserModel> user = new ArrayList<>();
        while(!cursor.isAfterLast()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_ID));
            String  dateOfBirth= cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATE_OF_BIRTH));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(KEY_EMAIL));

            user.add(new UserModel(id,name,dateOfBirth,email));

            cursor.moveToNext();
        }
        cursor.close();

        return user;
    }
 }
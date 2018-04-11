package com.example.apurva.sqlitedatabase;


import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//to perform CRUD operation we need to extends SQLiteOpenHelper class
public class DatabaseFile extends SQLiteOpenHelper {

    public  static final  int DATABASE_VERSION=1;

    //database name
    public static  final String DATABASE_NAME="mydb";

    //table name
    public static final String TABLE_NAME="criminal_ka_data";

    //column name
    public static final String CRIMINAL_ID="id";
    public static final String CRIMINAL_NAME="name";
    public static final String CRIMINAL_DISP="disp";


    //used to create database it constructor take database name and version and create a database

    public DatabaseFile(Context context ){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        //oncreate automatically called after database is creater (after constuctor called and return database ref to db)


        String query="CREATE TABLE " + TABLE_NAME + "(" + CRIMINAL_ID + " NUMBER PRIMARY KEY," + CRIMINAL_NAME + " TEXT," + CRIMINAL_DISP + " TEXT"+ ");";

        //to create a table
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //onCreate(db);
    }
}

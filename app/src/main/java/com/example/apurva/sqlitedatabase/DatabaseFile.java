package com.example.apurva.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//to perform CRUD operation we need to extends SQLiteOpenHelper class
public class DatabaseFile extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    //database name
    public static final String DATABASE_NAME = "mydb";

    //table name
    public static final String TABLE_NAME = "criminal_ka_data";

    //column name
    public static final String CRIMINAL_ID = "id";
    public static final String CRIMINAL_NAME = "name";
    public static final String CRIMINAL_DISP = "disp";


    //used to create database it constructor take database name and version and create a database

    public DatabaseFile(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        //oncreate automatically called after database is creater (after constuctor called and return database ref to db)


        String query = "CREATE TABLE " + TABLE_NAME + "(" + CRIMINAL_ID + " NUMBER PRIMARY KEY," + CRIMINAL_NAME + " TEXT," + CRIMINAL_DISP + " TEXT" + ");";

        //to create a table
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        //onCreate(db);
    }

    public void addCriminalRecord(CriminalRecord record) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CRIMINAL_ID, record.getId());
        values.put(CRIMINAL_NAME, record.getName());
        values.put(CRIMINAL_DISP, record.getDesp());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<CriminalRecord> getAllCriminalList() {

        SQLiteDatabase db = getReadableDatabase();
        ArrayList<CriminalRecord> records = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                CriminalRecord rec = new CriminalRecord();
                rec.setId(cursor.getInt(0));
                rec.setName(cursor.getString(1));
                rec.setDesp(cursor.getString(2));

                records.add(rec);
            } while (cursor.moveToNext());
        }
        return records;
    }

    public CriminalRecord getSingleRecord(int id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{CRIMINAL_ID, CRIMINAL_NAME, CRIMINAL_DISP}, CRIMINAL_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        CriminalRecord rec=new CriminalRecord();
        if (cursor != null) {
            cursor.moveToNext();


           rec = new CriminalRecord(cursor.getInt(0), cursor.getString(1), cursor.getString(2));


        }
        return rec;

    }

    public void updateRecord(CriminalRecord record){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CRIMINAL_DISP,record.getDesp());

        db.update(TABLE_NAME,values,CRIMINAL_ID+"=?",new String[]{String.valueOf(record.getId())});
        db.close();
    }

    public void deleteRec(int id){
        SQLiteDatabase db=getWritableDatabase();

        db.delete(TABLE_NAME,CRIMINAL_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
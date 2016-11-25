package com.example.admin.mynewreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Laurah on 2016-11-22.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

//creating StudentInformation

    public static final String DATABASE_NAME="STUDENTS_INFORMATION.db";
    public  static final String TABLE_NAME="STUDENT_INFORMATION";
    public  static final String col_Full_Names="Full_Names";
    public  static final String col_Surname="Surname";
    public  static final String col_ID="ID";
    public  static final String col_Nationality="Nationality";
    public  static final String col_Gender="Gender";
    public  static final String col_Language="Language";
    public  static final String col_Contacts="Contacts";
    public  static final String col_Next_Of_Kin="Next_Of_Kin";
    public  static final String col_Next_Of_Kin_Contacts="Next_Of_Kin_Contacts";
    public  static final String col_Email="Email";
    public  static final String col_Physical_Address=" Physical_Address";
    public  static final String col_Grade="Grade";


    public DatabaseHelper(Context context) {
        super(context,TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE STUDENT_INFORMATION ( ID INTEGER PRIMARY KEY AUTOINCREMENT, FULL_NAMES TEXT, SURNAME TEXT, NATIONALITY TEXT, GENDER TEXT, LANGUAGE TEXT, CONTACTS TEXT, NEXT_OF_KIN TEXT, NEXT_OF_KIN_CONTACTS TEXT, " +
                "EMAIL TEXT, PHYSICAL_ADDRESS TEXT, GRADE TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP THE TABLE IF EXISTS" + TABLE_NAME);
        onCreate(db);

    }
    public  boolean insert(String Full_names, String Surname,
                           String Nationality, String Gender, String Language,
                           String Contacts, String Next_Of_Kin,
                           String Next_Of_Kin_Contacts,
                           String Email, String Physical_Address,
                           String Grade){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_Full_Names,Full_names);
        contentValues.put(col_Surname,Surname);
        contentValues.put(col_Nationality,Nationality);
        contentValues.put(col_Gender,Gender);
        contentValues.put(col_Language,Language);
        contentValues.put(col_Contacts,Contacts);
        contentValues.put(col_Next_Of_Kin,Next_Of_Kin);
        contentValues.put(col_Next_Of_Kin_Contacts,Next_Of_Kin_Contacts);
        contentValues.put(col_Email,Email);
        contentValues.put(col_Physical_Address,Physical_Address);
        contentValues.put(col_Grade,Grade);

        long information =db.insert(TABLE_NAME,null,contentValues);
        if (information ==-1){
            return  false;
        }
        else
            return  true;

    }
    public Cursor GetAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor Results=db.rawQuery("select * from " + TABLE_NAME, null);
        return Results;
    }
    public boolean UpdateData(String ID, String Full_Names, String Surname, String Nationality, String Gender, String Language, String Contacts, String Next_Of_Kin, String Next_Of_Kin_Contacts, String Email, String Physical_Address, String Grade){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(col_ID,ID);
        contentValues.put(col_Full_Names,Full_Names);
        contentValues.put(col_Surname,Surname);
        contentValues.put(col_Nationality,Nationality);
        contentValues.put(col_Gender,Gender);
        contentValues.put(col_Language,Language);
        contentValues.put(col_Contacts,Contacts);
        contentValues.put(col_Next_Of_Kin,Next_Of_Kin);
        contentValues.put(col_Next_Of_Kin_Contacts,Next_Of_Kin_Contacts);
        contentValues.put(col_Email,Email);
        contentValues.put(col_Physical_Address,Physical_Address);
        contentValues.put(col_Grade,Grade);

        db.update(TABLE_NAME,contentValues, " ID = ?" ,new  String[] {ID});
        return  true;

  }
    public  Integer deleteData (String ID){
        SQLiteDatabase db=this.getWritableDatabase();
        return  db.delete(TABLE_NAME, " ID = ?",new  String[]{ID});
    }



}
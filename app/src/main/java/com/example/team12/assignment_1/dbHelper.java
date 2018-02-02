package com.example.team12.assignment_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Benedict Tang on 26-Jan-18.
 */

public class dbHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Assignment1.db";
    public static final String TABLE_NAME = "profile_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AGE";
    public static final String COL_4 = "HOBBIES";
    public static final String COL_5 = "SCHOOL";
    public static final String COL_6 = "COURSE";
    public static final String COL_7 = "CONTACT_NUMBER";
    public static final String COL_8 = "EMAIL";
    public static final String COL_9 = "PROFILE_PICTURE";


    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID TEXT PRIMARY KEY, NAME TEXT, AGE INTERGER, HOBBIES TEXT, SCHOOL TEXT, COURSE TEXT, CONTACT_NUMBER INTEGER, EMAIL TEXT, PROFILE_PICTURE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertAllField(String id, String name, int age, String hobbies, String school, String course, int contact, String email, String profile){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        Log.d("NAME", name);
        Log.d("AGE", ""+age);
        cv.put(COL_1, id);
        cv.put(COL_2, name);
        cv.put(COL_3, age);
        cv.put(COL_4,hobbies);
        cv.put(COL_5,school);
        cv.put(COL_6, course);
        cv.put(COL_7,contact);
        cv.put(COL_8,email);
        cv.put(COL_9,profile);
        long result = db.insert(TABLE_NAME,null,cv);
        if(result == -1)
        return false;
        else
            return true;
    }

    public Cursor getAllResult(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return rs;
    }

    public boolean updatePic(HumanClass hc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, hc.getId());
        cv.put(COL_2, hc.getName());
        cv.put(COL_3, hc.getAge());
        cv.put(COL_4,hc.getHobbies());
        cv.put(COL_5,hc.getSchool());
        cv.put(COL_6,hc.getCourse());
        cv.put(COL_7,hc.getContact());
        cv.put(COL_8,hc.getEmail());
        cv.put(COL_9,hc.getProfile());
        db.update(TABLE_NAME, cv, "ID = ?", new String[] {hc.getId()});
        return true;
    }

}

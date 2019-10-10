package com.example.studentsenrollment.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "InsuranceInspectionDB";

    public enum Column {

        ID("id"),
        NAME("name"),
        MOBILE("mobile"),
        ADDRESS("address"),
        GENDER("gender"),
        DOB("dob"),
        CLASS("class"),
        DEPARTMENT("department");

        Column(String mColumnName) {
            this.mColumnName = mColumnName;
        }

        String mColumnName;

        public String getmColumnName() {
            return mColumnName;
        }
    }

    public static final String TABLE_STUDENT_DETAILS = "studentDetailsTable";

    private static final String CREATE_TABLE_STUDENT_DETAILS = "CREATE TABLE if not exists "
            + TABLE_STUDENT_DETAILS + " ("
            + Column.ID.getmColumnName() + " TEXT PRIMARY KEY , "
            + Column.NAME.getmColumnName() + " TEXT , "
            + Column.MOBILE.getmColumnName() + " TEXT , "
            + Column.ADDRESS.getmColumnName() + " TEXT , "
            + Column.GENDER.getmColumnName() + " TEXT , "
            + Column.DOB.getmColumnName() + " TEXT , "
            + Column.CLASS.getmColumnName() + " TEXT , "
            + Column.DEPARTMENT.getmColumnName() + " TEXT "
            + " )";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_STUDENT_DETAILS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_DETAILS);
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
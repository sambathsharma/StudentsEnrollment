package com.example.studentsenrollment.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String TABLE_STUDENT_DETAILS = "studentDetailsTable";
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "InsuranceInspectionDB";
    private static final String CREATE_TABLE_STUDENT_DETAILS = "CREATE TABLE if not exists "
            + TABLE_STUDENT_DETAILS + " ("
            + Column.ID.getmColumnName() + " TEXT PRIMARY KEY , "
            + Column.NAME.getmColumnName() + " TEXT , "
            + Column.MOBILE.getmColumnName() + " TEXT , "
            + Column.ADDRESS.getmColumnName() + " TEXT , "
            + Column.GENDER.getmColumnName() + " TEXT , "
            + Column.DOB.getmColumnName() + " TEXT , "
            + Column.YEAR.getmColumnName() + " TEXT , "
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

    public void insertStudentDetails(final String name, final String mobile,
                                     final String address, final String gender,
                                     final String dob, final String year,
                                     final String department) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();

            values.put(Column.NAME.getmColumnName(), name);
            values.put(Column.MOBILE.getmColumnName(), mobile);
            values.put(Column.ADDRESS.getmColumnName(), address);
            values.put(Column.GENDER.getmColumnName(), gender);
            values.put(Column.DOB.getmColumnName(), dob);
            values.put(Column.YEAR.getmColumnName(), year);
            values.put(Column.DEPARTMENT.getmColumnName(), department);
            db.insert(TABLE_STUDENT_DETAILS, null, values);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public enum Column {

        ID("id"),
        NAME("name"),
        MOBILE("mobile"),
        ADDRESS("address"),
        GENDER("gender"),
        DOB("dob"),
        YEAR("year"),
        DEPARTMENT("department");

        String mColumnName;

        Column(String mColumnName) {
            this.mColumnName = mColumnName;
        }

        public String getmColumnName() {
            return mColumnName;
        }
    }
}
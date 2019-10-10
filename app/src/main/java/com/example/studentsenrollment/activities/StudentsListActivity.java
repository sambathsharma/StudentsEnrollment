package com.example.studentsenrollment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentsenrollment.R;
import com.example.studentsenrollment.adapters.StudentDetailsAdapter;
import com.example.studentsenrollment.db.DatabaseHandler;
import com.example.studentsenrollment.models.StudentDetailsModel;

import java.util.ArrayList;

public class StudentsListActivity extends AppCompatActivity {

    RecyclerView rv;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        init();

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Cursor cursor = databaseHandler.getStudentDetails();

        Log.e("cursor", cursor.getCount() + "-=-=-");

        if (cursor != null && cursor.getCount() > 0) {
            tv.setVisibility(View.GONE);
            ArrayList<StudentDetailsModel> arrayList = new ArrayList<>();

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                StudentDetailsModel studentDetailsModel = new StudentDetailsModel();
                studentDetailsModel.setName(cursor.getString(
                        cursor.getColumnIndex(DatabaseHandler.Column.NAME.getmColumnName())));
                studentDetailsModel.setMobile(cursor.getString(
                        cursor.getColumnIndex(DatabaseHandler.Column.MOBILE.getmColumnName())));
                studentDetailsModel.setAddress(cursor.getString(
                        cursor.getColumnIndex(DatabaseHandler.Column.ADDRESS.getmColumnName())));
                studentDetailsModel.setGender(cursor.getString(
                        cursor.getColumnIndex(DatabaseHandler.Column.GENDER.getmColumnName())));
                studentDetailsModel.setDob(cursor.getString(
                        cursor.getColumnIndex(DatabaseHandler.Column.DOB.getmColumnName())));
                studentDetailsModel.setYear(cursor.getString(
                        cursor.getColumnIndex(DatabaseHandler.Column.YEAR.getmColumnName())));
                studentDetailsModel.setDepartment(cursor.getString(
                        cursor.getColumnIndex(DatabaseHandler.Column.DEPARTMENT.getmColumnName())));

                arrayList.add(studentDetailsModel);
                cursor.moveToNext();
            }
            cursor.close();

            StudentDetailsAdapter studentDetailsAdapter = new StudentDetailsAdapter(StudentsListActivity.this,
                    arrayList);
            rv.setAdapter(studentDetailsAdapter);

        } else {
            tv.setVisibility(View.VISIBLE);
        }

    }

    private void init() {
        ImageView iv_back = findViewById(R.id.iv_back);
        rv = findViewById(R.id.rv);
        tv = findViewById(R.id.tv);
        tv.setVisibility(View.GONE);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

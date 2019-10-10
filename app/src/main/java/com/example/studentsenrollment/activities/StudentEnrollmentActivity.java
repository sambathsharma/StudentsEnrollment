package com.example.studentsenrollment.activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentsenrollment.R;
import com.example.studentsenrollment.adapters.StringSpinnerAdapter;
import com.example.studentsenrollment.db.DatabaseHandler;
import com.example.studentsenrollment.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;

public class StudentEnrollmentActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_name, et_mobile, et_address, et_dob;
    RadioGroup radio_group;
    RadioButton radio_male, radio_female, radio_trans;
    Spinner spinner_year, spinner_department;

    String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enrollment);

        init();

    }

    private void init() {
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
        et_name = findViewById(R.id.et_name);
        et_mobile = findViewById(R.id.et_mobile);
        et_address = findViewById(R.id.et_address);
        radio_group = findViewById(R.id.radio_group);
        radio_male = findViewById(R.id.radio_male);
        radio_female = findViewById(R.id.radio_female);
        radio_trans = findViewById(R.id.radio_trans);
        et_dob = findViewById(R.id.et_dob);
        et_dob.setOnClickListener(this);
        spinner_year = findViewById(R.id.spinner_year);
        spinner_department = findViewById(R.id.spinner_department);


        setupClass();
        setupDepartment();
    }

    private void setupClass() {
        ArrayList<String> classList = Constants.getYears();
        classList.add(0, Constants.SELECT_YEAR);

        StringSpinnerAdapter adapter = new StringSpinnerAdapter(StudentEnrollmentActivity.this,
                R.layout.spinner_text, classList);
        spinner_year.setAdapter(adapter);
    }

    private void setupDepartment() {
        ArrayList<String> departmentList = Constants.getDepartments();
        departmentList.add(0, Constants.SELECT_DEPARTMENT);

        StringSpinnerAdapter adapter = new StringSpinnerAdapter(StudentEnrollmentActivity.this,
                R.layout.spinner_text, departmentList);
        spinner_department.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                onBackPressed();
                break;

            case R.id.btn:

                if (checkEnrollment()) {
                    DatabaseHandler databaseHandler = new DatabaseHandler(this);
                    databaseHandler.insertStudentDetails(et_name.getText().toString(),
                            et_mobile.getText().toString(), et_address.getText().toString(),
                            gender, et_dob.getText().toString(), spinner_year.getSelectedItem().toString(),
                            spinner_department.getSelectedItem().toString());
                    Toast.makeText(StudentEnrollmentActivity.this, "Enrollment for "
                            + et_name.getText().toString() + " is done successfully", Toast.LENGTH_LONG).show();
                    onBackPressed();
                }

                break;

            case R.id.et_dob:
                showDatePicker(et_dob);
                break;
        }
    }

    private void showDatePicker(final TextView textView) {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(StudentEnrollmentActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        textView.setText(getDate(year, month, dayOfMonth));
                    }
                },
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setCancelable(false);
        datePickerDialog.show();
    }

    private String getDate(int year, int month, int dayOfMonth) {
        StringBuilder date = new StringBuilder();
        date.append(year);
        date.append("-");
        month = month + 1;
        if (month < 10) {
            date.append("0");
        }
        date.append(month);
        date.append("-");
        if (dayOfMonth < 10) {
            date.append("0");
        }
        date.append(dayOfMonth);
        return date.toString();
    }

    private boolean checkEnrollment() {

        if (et_name.getText().toString().trim().isEmpty()) {
            Toast.makeText(StudentEnrollmentActivity.this, "Name cannot be empty", Toast.LENGTH_LONG).show();
            return false;
        }

        if (et_mobile.getText().toString().isEmpty()) {
            Toast.makeText(StudentEnrollmentActivity.this, "Mobile number cannot be empty", Toast.LENGTH_LONG).show();
            return false;
        }

        if (et_mobile.getText().toString().length() != 10) {
            Toast.makeText(StudentEnrollmentActivity.this, "Invalid mobile number", Toast.LENGTH_LONG).show();
            return false;
        }

        if (et_address.getText().toString().isEmpty()) {
            Toast.makeText(StudentEnrollmentActivity.this, "Address cannot be empty", Toast.LENGTH_LONG).show();
            return false;
        }

        if (radio_group.getCheckedRadioButtonId() == R.id.radio_male) {
            gender = "Male";
        } else if (radio_group.getCheckedRadioButtonId() == R.id.radio_female) {
            gender = "Female";
        } else if (radio_group.getCheckedRadioButtonId() == R.id.radio_trans) {
            gender = "Trans";
        }
        if (gender.isEmpty()) {
            Toast.makeText(StudentEnrollmentActivity.this, "Please select a gender", Toast.LENGTH_LONG).show();
            return false;
        }

        if (et_dob.getText().toString().isEmpty()) {
            Toast.makeText(StudentEnrollmentActivity.this, "Please select the DOB", Toast.LENGTH_LONG).show();
            return false;
        }

        if (spinner_year.getSelectedItem().toString().equals(Constants.SELECT_YEAR)) {
            Toast.makeText(StudentEnrollmentActivity.this, "Please select a year", Toast.LENGTH_LONG).show();
            return false;
        }

        if (spinner_department.getSelectedItem().toString().equals(Constants.SELECT_DEPARTMENT)) {
            Toast.makeText(StudentEnrollmentActivity.this, "Please select a department", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

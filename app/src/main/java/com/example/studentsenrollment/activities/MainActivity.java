package com.example.studentsenrollment.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.studentsenrollment.R;
import com.example.studentsenrollment.utils.Constants;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout cv_enroll, cv_view_enroll, cv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        cv_enroll = findViewById(R.id.cv_enroll);
        cv_enroll.setOnClickListener(this);
        cv_view_enroll = findViewById(R.id.cv_view_enroll);
        cv_view_enroll.setOnClickListener(this);
        cv_logout = findViewById(R.id.cv_logout);
        cv_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.cv_enroll :

                break;

            case R.id.cv_view_enroll:

                break;

            case R.id.cv_logout:
                SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGIN_PREFERENCE, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(Constants.IS_LOGGED_IN, false);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;
        }

    }
}

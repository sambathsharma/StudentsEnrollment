package com.example.studentsenrollment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentsenrollment.R;
import com.example.studentsenrollment.utils.Constants;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_user_id, et_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    private void init() {
        et_user_id = findViewById(R.id.et_user_id);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login){
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGIN_PREFERENCE, MODE_PRIVATE);
            if (!et_user_id.getText().toString().isEmpty()){
                if (!et_password.getText().toString().isEmpty()){
                    if (et_user_id.getText().toString().equals(sharedPreferences.getString(Constants.USERNAME, ""))){
                        if (et_password.getText().toString().equals(sharedPreferences.getString(Constants.PASSWORD, ""))){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean(Constants.IS_LOGGED_IN, true);
                            editor.apply();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Invalid user id", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter user id", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

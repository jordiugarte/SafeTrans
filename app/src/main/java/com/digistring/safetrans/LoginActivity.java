package com.digistring.safetrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.digistring.safetrans.dataBase.ConexionSQLiteHelper;

public class LoginActivity extends AppCompatActivity {

    private TextView userField;
    private TextView passwordField;

    private ConexionSQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setListeners();


    }

    private void setListeners() {
        userField = findViewById(R.id.userField);
        passwordField = findViewById(R.id.passwordField);
    }
}

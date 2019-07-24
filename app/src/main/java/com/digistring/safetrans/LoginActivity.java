package com.digistring.safetrans;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.digistring.safetrans.tools.Notification;

import com.digistring.safetrans.dataBase.ConexionSQLiteHelper;

public class LoginActivity extends AppCompatActivity {

    private TextView userField;
    private TextView passwordField;
    private Button loginButon;

    private Context context;
    private ConexionSQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();
        setListeners();

        loginButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "adasdas", Toast.LENGTH_SHORT);
                if (userField.toString() == "" && passwordField.toString() == "") {
                    new Notification("Ingrese datos válidos", context);
                }
            }
        });
    }

    private void setListeners() {
        userField = findViewById(R.id.userField);
        passwordField = findViewById(R.id.passwordField);
        loginButon = findViewById(R.id.loginButton);
    }
}

package com.digistring.safetrans;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digistring.safetrans.tools.Notification;

import com.digistring.safetrans.dataBase.ConexionSQLiteHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText userField;
    private EditText passwordField;
    private Button loginButon;
    private Button registerButon;

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
                String user = userField.getText().toString();
                String password = passwordField.getText().toString();
                if (validLogin(user, password)){
                    Intent main = new Intent(context, MainActivity.class);
                    startActivity(main);
                } else {
                    new Notification("Ingrese datos válidos", context);
                }
            }
        });
        registerButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(context, RegisterActivity.class);
                startActivity(main);
            }
        });
    }

    public static boolean validLogin(String email, String password){
        return validInfo(email, password) && validData(email, password);
    }

    public static boolean validInfo(String email, String password){
        //comparar con base de datos
        return true;
    }

    public static boolean validData(String email, String password) {
        String expression1 = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern1 = Pattern.compile(expression1, Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(email);
        String expression2 = "[^A-Za-z0-9]";
        Pattern pattern2 = Pattern.compile(expression2, Pattern.CASE_INSENSITIVE);
        Matcher matcher2 = pattern2.matcher(password);
        //matcher1.matches() && matcher2.matches();
        return true;
        // && (!TextUtils.isEmpty(password) || password.length() > 7 && password.length() < 33)
    }

    private void setListeners() {
        userField = findViewById(R.id.userField);
        passwordField = findViewById(R.id.passwordField);
        loginButon = findViewById(R.id.loginButton);
        registerButon = findViewById(R.id.registerButton);
    }
}

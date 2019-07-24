package com.digistring.safetrans;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

                if (isEmailValid(user) || !password.equals("")){
                    //Verificar usuario y contrasena
                } else {
                    new Notification("Ingrese datos válidos", context);
                }
            }
        });
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void setListeners() {
        userField = findViewById(R.id.userField);
        passwordField = findViewById(R.id.passwordField);
        loginButon = findViewById(R.id.loginButton);
    }
}

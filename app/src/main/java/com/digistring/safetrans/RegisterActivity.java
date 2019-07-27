package com.digistring.safetrans;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.digistring.safetrans.dataBase.DataBaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    private EditText textId;
    private EditText textPassword;
    private EditText textName;
    private EditText textIncome;

    DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textId = findViewById(R.id.id);
        textPassword = findViewById(R.id.password);
        textName = findViewById(R.id.name);
        textIncome = findViewById(R.id.income);
        helper = new DataBaseHelper(this);
    }

    public void register(View view) {
        int id = Integer.parseInt(textId.getText().toString());
        String password = textPassword.getText().toString();
        String name = textName.getText().toString();
        int income = Integer.parseInt(textIncome.getText().toString());
        if( !helper.login(id,password)) {
            helper.insertUser(id, password, name, income);
            Intent main = new Intent(this, LoginActivity.class);
            startActivity(main);
        }
    }

}

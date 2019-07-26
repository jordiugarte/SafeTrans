package com.digistring.safetrans;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.digistring.safetrans.tools.Notification;
import com.digistring.safetrans.dataBase.ConexionSQLiteHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText userField;
    private EditText passwordField;
    private Button loginButon;
    private Button registerButon;
    private Context context;
    private ConexionSQLiteHelper sqLiteHelper;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("oneTimeLogin", MODE_PRIVATE);

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

    public boolean validLogin(String email, String password){
        return validInfo(email, password) && validData(email, password);
    }

    public boolean validInfo(final String email, String password){
        //comparar con base de datos
        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.show();

        //log in user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent intencion = new Intent(getApplication(), MainActivity.class);
                            //implementacion onelogin
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("signInValue", true);
                            editor.apply();
                            editor.commit();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(LoginActivity.this, "Usuario o contrasena invalidos", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Usuario o contrasena invalidos", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });



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

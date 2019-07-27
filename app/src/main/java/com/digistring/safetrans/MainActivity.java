package com.digistring.safetrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.digistring.safetrans.dataBase.ConexionSQLiteHelper;
import com.digistring.safetrans.dataBase.DataBaseHelper;
import com.digistring.safetrans.tools.Clock;
import com.digistring.safetrans.tools.Notification;
import com.digistring.safetrans.tools.Process;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView clockView;
    private TextView dateView;
    private TextView accountField;
    private TextView amountField;
    private TextView processButton;
    private TextView userView;
    private TextView idView;
    private TextView incomeView;
    private TextView accountView;

    private int id;
    private int thisAccount;
    private Clock clock;
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListeners();
        updateClockView();
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db test_users", null, 1);
        DataBaseHelper dbH = new DataBaseHelper(getApplicationContext());

        id = getIntent().getIntExtra("id", 0);

        userView.setText("Nombre: " + dbH.getPersonalData(id)[1]);
        idView.setText("CI: " + id);
        incomeView.setText("N de Cuenta: " + dbH.getAccount(id));
        accountView.setText("Ingreso mensual: " + dbH.getPersonalData(id)[2]);

        thisAccount = dbH.getAccount(id);
        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int account = Integer.parseInt(accountField.getText().toString());
                int amount = Integer.parseInt(amountField.getText().toString());
                process(account, amount);
            }
        });
    }

    private void updateClockView() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar calendar = Calendar.getInstance();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                clock = new Clock();
                                clockView.setText(clock.hours + ":" + clock.minutes + ":" + clock.seconds);
                                dateView.setText(dateFormat.format(calendar.getTime()));
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    private void setListeners() {
        clockView = findViewById(R.id.clockView);
        dateView = findViewById(R.id.dateView);
        accountField = findViewById(R.id.accountField);
        amountField = findViewById(R.id.mountField);
        processButton = findViewById(R.id.processButton);
        userView = findViewById(R.id.nameDescription);
        idView = findViewById(R.id.idDescription);
        incomeView = findViewById(R.id.incomeDescription);
        accountView = findViewById(R.id.accountDescription);
    }

    private void process(int account, int amount) {
        Process p = new Process(getApplicationContext(), clock, thisAccount, account, amount, id);
        boolean validAmount = p.amountValidation();
        if (validAmount) {
            new Notification("Transaccion realizada con exito", getApplicationContext());
        } else {
            new Notification("No se puede realizar la transaccion", getApplicationContext());
        }
    }
}
package com.digistring.safetrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.digistring.safetrans.dataBase.ConexionSQLiteHelper;
import com.digistring.safetrans.tools.Clock;
import com.digistring.safetrans.tools.Process;

public class MainActivity extends AppCompatActivity {
    private TextView clockView;
    private TextView accountField;
    private TextView amountField;
    private TextView processButton;

    private Clock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListeners();
        updateClockView();
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db test_users", null, 1);

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountField.getText().toString();
                int amount = Integer.parseInt(amountField.getText().toString());
                process(account, amount);
            }
        });
    }

    private void updateClockView(){
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    while (!isInterrupted()){
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                clock = new Clock();
                                clockView.setText(clock.hours + ":" + clock.minutes + ":" + clock.seconds);
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
        accountField = findViewById(R.id.accountField);
        amountField = findViewById(R.id.mountField);
        processButton = findViewById(R.id.processButton);
    }

    private void process(String account, int amount) {
        new Process(new Clock(), account, amount);
    }
}
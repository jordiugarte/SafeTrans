package com.digistring.safetrans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.digistring.safetrans.dataBase.ConexionSQLiteHelper;
import com.digistring.safetrans.tools.Clock;
import com.digistring.safetrans.tools.Process;

public class MainActivity extends AppCompatActivity {
    private TextView clockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListeners();
        updateClockView();
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "db test_users", null, 1);
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
                                clockView.setText(new Clock().hours + ":" + new Clock().minutes + ":" + new Clock().seconds);
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
    }

    private void process() {
        new Process(new Clock());
    }
}

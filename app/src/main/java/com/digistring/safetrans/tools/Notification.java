package com.digistring.safetrans.tools;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class Notification extends Application {
    public Notification(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

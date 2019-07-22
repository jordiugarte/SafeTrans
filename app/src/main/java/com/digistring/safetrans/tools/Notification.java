package com.digistring.safetrans.tools;

import android.content.Context;
import android.widget.Toast;

public class Notification {
    public Notification(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT);
    }
}

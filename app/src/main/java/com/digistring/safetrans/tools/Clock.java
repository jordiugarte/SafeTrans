package com.digistring.safetrans.tools;

import java.util.Calendar;

public class Clock {
    public int hours;
    public int minutes;
    public int seconds;

    public Clock () {
        Calendar cal = Calendar.getInstance();
        hours = cal.get(Calendar.HOUR);
        minutes = cal.get(Calendar.MINUTE);
        seconds = cal.get(Calendar.SECOND);
    }
}

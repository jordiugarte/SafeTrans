package com.digistring.safetrans.tools;

public class Process {
    private int RTHours;
    private int RTMinutes;
    private int RTSeconds;

    private int lastHours;
    private int lastMinutes;
    private int lastSeconds;

    public Process(Clock clock) {
        RTHours = clock.hours;
        RTMinutes = clock.minutes;
        RTSeconds = clock.seconds;


    }

    public boolean validation(int hours, int minutes, int seconds) {

        return false;
    }

    private boolean timeValidation(int hours, int minutes, int seconds) {

        return false;
    }
}

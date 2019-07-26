package com.digistring.safetrans.tools;

public class Process {
    private int RTHours;
    private int RTMinutes;
    private int RTSeconds;

    private int lastHours;
    private int lastMinutes;
    private int lastSeconds;

    private int income;

    public Process(Clock clock, String user, int amount) {
        RTHours = clock.hours;
        RTMinutes = clock.minutes;
        RTSeconds = clock.seconds;

        /*lastHours = */
        /*lastMinutes = */
        /*lastSeconds = */

        /*income = */
    }

    public boolean amountValidation(int hours, int minutes, int seconds) {
        return false;
    }

    private boolean timeValidation(int hours, int minutes, int seconds) {
        return false;
    }
}

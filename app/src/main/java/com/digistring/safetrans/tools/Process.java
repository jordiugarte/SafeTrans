package com.digistring.safetrans.tools;

import java.util.Calendar;
import java.util.Date;

public class Process {
    private Calendar calendar;

    private static int RTHours;
    private static int lastHours;

    private static Date RTDate;
    private static Date lastDate;

    private int lastAmount;
    private int RTAmount;

    private int income;

    public Process(Clock clock, int account, int amount) {
        RTHours = clock.hours;
        /*lastHours = */
        RTDate = calendar.getTime();

        /*lastHours = */
        /*lastDate = */

        /*income = */
    }

    public boolean amountValidation(int hours, int minutes, int seconds) {
        if (hoursBetween(RTDate, lastDate) / 10 < income * 2) {
            return false;
        } else {

        }
        return true;
    }

    private float constant() {
        long hoursDifference = hoursBetween(RTDate, lastDate);
        return hoursDifference / 10;
    }

    private static long hoursBetween(Date one, Date two) {
        int hoursDifference = (int) (daysBetween(one, two)) * 24;
        int plusHours = RTHours - lastHours;
        long difference = hoursDifference + plusHours;
        return difference;
    }

    private static long daysBetween(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / 86400000;
        return Math.abs(difference);
    }
}

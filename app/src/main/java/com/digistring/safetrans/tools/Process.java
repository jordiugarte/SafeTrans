package com.digistring.safetrans.tools;

import android.content.ContentValues;
import android.content.Context;

import com.digistring.safetrans.dataBase.DataBaseHelper;

import java.util.Calendar;
import java.util.Date;

public class Process {
    private Context context;
    private Calendar calendar;

    private static int RTHours;
    private static int lastHours;

    private static Date RTDate;
    private static Date lastDate;

    private int lastAmount;
    private int RTAmount;

    private int income;

    public Process(Context context, Clock clock, int account1, int account2, int amount, int id) {
        this.context = context;
        calendar = Calendar.getInstance();
        DataBaseHelper dbH = new DataBaseHelper(context);
        RTHours = clock.hours;
        //lastHours = dbH.getLastDate()
        RTDate = calendar.getTime();
        /*lastDate = */
        RTAmount = amount;
        income = Integer.parseInt(dbH.getPersonalData(id)[2]);
    }

    public boolean amountValidation() {
        if (RTAmount > income) {
            if (((float) (RTAmount - income) / (float) RTAmount) > 0.9f) {
                if (true) {
                   return false;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
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

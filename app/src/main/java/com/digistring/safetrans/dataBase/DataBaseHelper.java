package com.digistring.safetrans.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBaseHelper {
    private SQLiteDatabase database;
    DataBase instancia;

    public DataBaseHelper(Context context) {
        instancia = new DataBase(context);
        this.database = instancia.getWritableDatabase();
    }

    public Cursor getALLUserData() {
        SQLiteDatabase db = instancia.getWritableDatabase();
        Cursor res = db.rawQuery("select * from user", null);
        return res;
    }

    public Cursor getAccountData() {
        SQLiteDatabase db = instancia.getWritableDatabase();
        Cursor res = db.rawQuery("select * from account", null);
        return res;
    }

    public Cursor getTransData() {
        SQLiteDatabase db = instancia.getWritableDatabase();
        Cursor res = db.rawQuery("select * from trans", null);
        return res;
    }

    public String[] getPersonalData(int id) {
        String[] data = new String[3];
        Cursor res = getALLUserData();
        while (res.moveToNext()) {
            if (Integer.parseInt(res.getString(0)) == id) {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
            }
        }
        return data;
    }

    public int getAccount(int id) {
        int account = 0;
        Cursor res = getAccountData();
        while (res.moveToNext()) {
            if (Integer.parseInt(res.getString(1)) == id) {
                account = res.getInt(0);
            }
        }
        return account;
    }

    /*public Date getLastDate(int id) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateStr;
        Cursor res = getTransData();
        while (res.moveToNext()) {
            if (Integer.parseInt(res.getString(0)) == id) {
                String temp = res.getString(2);
                date = dateFormat.parse(temp);
            }
        }
        Date date = dateFormat.format(Date);
        return new date;
    }*/

    public boolean login(int id_user, String password) {
        Cursor res = getALLUserData();
        if (res.getCount() == 0) {
            return false;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            if (Integer.parseInt(res.getString(0)) == id_user && res.getString(1).equals(password)) {
                return true;
            }
            //buffer .append("id :" + res.getString(0));
        }
        return false;
    }
        /*IF NOT EXISTS(SELECT 1 FROM EVENTTYPE WHERE EventTypeName = 'ANI Received')
    INSERT INTO EVENTTYPE (EventTypeName) VALUES ('ANI Received');

        String[] temp = new String[2];
        temp[0] = Integer.toString(id_user);
        temp[1] = password;

        Cursor cursor = this.database.rawQuery("SELECT id FROM user" +
                " WHERE user = ? AND password = ?", temp);

        if (cursor.moveToFirst()) {
            Log.d("id", "" + cursor.getInt(0));
            return true;
        } else {
            return false;
        }*/

    public void insertUser(int id, String password, String name, int income) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("income", income);

        ContentValues accountValues = new ContentValues();
        accountValues.putNull("id");
        accountValues.put("id_user", id);
        accountValues.put("amount", 100000);

        this.database.insert("user", null, contentValues);
        this.database.insert("account", null, accountValues);
    }
}

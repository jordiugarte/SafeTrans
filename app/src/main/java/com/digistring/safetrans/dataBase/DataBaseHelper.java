package com.digistring.safetrans.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseHelper {
    private SQLiteDatabase database;

    public DataBaseHelper(Context context) {
        DataBase instancia = new DataBase(context);
        this.database = instancia.getWritableDatabase();
    }

    public boolean login(int id_user, String password) {

        Cursor cursor = this.database.rawQuery("");

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
    }

    public void insertUser (int id, String password, String name, int income){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("password", password);
        contentValues.put("name", name);
        contentValues.put("income", income);

        this.database.insert("user", null, contentValues);
        this.database.close();
    }
}

package com.digistring.safetrans.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataBaseHelper {
    private SQLiteDatabase database;
    DataBase instancia;

    public DataBaseHelper(Context context) {
        instancia = new DataBase(context);
        this.database = instancia.getWritableDatabase();
    }

    public Cursor getALLData (){
        SQLiteDatabase db = instancia.getWritableDatabase();
        Cursor res = db.rawQuery("select * from user" ,null  );
        return  res;
    }

    public boolean login(int id_user, String password) {
        Cursor res = getALLData();
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

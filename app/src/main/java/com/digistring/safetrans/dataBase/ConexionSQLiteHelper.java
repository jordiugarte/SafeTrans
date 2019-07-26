package com.digistring.safetrans.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper  extends SQLiteOpenHelper {


    final String CREATE_TABLE_USERS="CREATE TABLE test_users( id INTEGER, password TEXT, time TIME, date DATE )";

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int ModeloAntiguo, int ModeloActual) {
        db.execSQL("DROP TABLE IF EXISTS test_users");
        onCreate(db);
    }
}

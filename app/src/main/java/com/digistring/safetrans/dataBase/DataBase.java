package com.digistring.safetrans.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context) {
        super(context,
                Constants.DATABASE_NAME,
                null,
                Constants.DATABASE_VERSION);
    }

    //First time
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE user ( id INTEGER PRIMARY KEY NOT NULL, " +
                " password VARCHAR NOT NULL, " +
                " name VARCHAR NOT NULL, " +
                " income INTEGER NOT NULL ) ");

        db.execSQL("CREATE TABLE account ( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                " id_user INTEGER, " +
                " amount INTEGER NOT NULL, " +
                " FOREIGN KEY (id_user) REFERENCES user(id))");

        db.execSQL("CREATE TABLE tran ( id_account1 INTEGER ," +
                "id_account2 INTEGER, " +
                " date DATETIME_REAL," +
                " amount INTEGER," +
                "FOREIGN KEY (id_account1) REFERENCES account (id), " +
                " FOREIGN KEY (id_account2 ) REFERENCES account(id))");

        Log.d("Database", "Created");

    }

    //Migración
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

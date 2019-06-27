package com.example.asistentedeahorro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccesoDB extends SQLiteOpenHelper {
    public AccesoDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "datos", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE IF NOT EXISTS movimientos (" +
            "idmovimiento INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  idtipomov TEXT NOT NULL," +
            "  idconcepto INTEGER DEFAULT NULL," +
            "  fecha TEXT DEFAULT NULL," +
            "  importe REAL DEFAULT NULL)");

    db.execSQL("CREATE TABLE IF NOT EXISTS conceptos (" +
            "idconcepto INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombre TEXT DEFAULT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}

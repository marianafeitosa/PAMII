package com.example.appsqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COL + " TEXT, " +
                END_COL + " TEXT, " +
                BAR_COL + " TEXT, " +
                CEP_COL + " TEXT, " +
                TEL_COL + " TEXT)")
        try {
            db?.execSQL(query)
            Log.d("DBHelper", "Database and table created successfully")
        } catch (e: Exception) {
            Log.e("DBHelper", "Error creating database and table", e)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addPessoa(name: String, endereco: String, bairro: String, cep: String, telefone: String) {
        val values = ContentValues()
        values.put(NAME_COL, name)
        values.put(END_COL, endereco)
        values.put(BAR_COL, bairro)
        values.put(CEP_COL, cep)
        values.put(TEL_COL, telefone)

        val db = this.writableDatabase
        val result = db.insert(TABLE_NAME, null, values)
        if (result == -1L) {
            Log.e("DBHelper", "Failed to insert data")
        } else {
            Log.d("DBHelper", "Data inserted successfully")
        }
        db.close()
    }


    companion object {
        private const val DATABASE_NAME = "appSQLite"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "CadastroPessoa"
        const val ID_COL = "id"
        const val NAME_COL = "Nome"
        const val END_COL = "Endereco"
        const val BAR_COL = "Bairro"
        const val CEP_COL = "CEP"
        const val TEL_COL = "Telefone"
    }
}

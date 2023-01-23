package com.example.myapplication
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class SqlHelper(context: Context, nothing: Nothing?):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "XYZ"
        private const val  TABLE_NAME= "Abcd"
        private const val ID = "id"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"



    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY,$NAME TEXT,$EMAIL TEXT,$PASSWORD TEXT)")


    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}

package com.instabug.instabugtask.data_layer.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context?) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_WORD_TABLE =
            ("CREATE TABLE " + TABLE_WORDS + "("
                    + KEY_ID + " INTEGER PRIMARY KEY," + KEY_WORD + " TEXT,"
                    + KEY_WORD_COUNT + " INTEGER" + ")")
        db.execSQL(CREATE_WORD_TABLE)
    }
    // Upgrading database
    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_WORDS")

        onCreate(db)
    }


    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "INSTABUG"
        const val TABLE_WORDS = "WORDS"
        const val KEY_ID = "ID"
        const val KEY_WORD = "WORD"
        const val KEY_WORD_COUNT = "WORD_COUNT"
    }
}
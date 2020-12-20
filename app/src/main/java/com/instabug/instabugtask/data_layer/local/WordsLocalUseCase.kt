package com.instabug.instabugtask.data_layer.local

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.instabug.instabugtask.data_layer.dto.DataResponse
import com.instabug.instabugtask.data_layer.dto.Word
import com.instabug.instabugtask.utils.App
import java.util.*


class WordsLocalUseCase {

    private lateinit var db: SQLiteDatabase

    fun addWords(words: List<Word>) {
        db = DatabaseHandler(App.context).writableDatabase
        db.execSQL("delete from "+ DatabaseHandler.TABLE_WORDS)
        for (word in words) {
            val values = ContentValues()
            values.put(DatabaseHandler.KEY_WORD, word.word)
            values.put(DatabaseHandler.KEY_WORD_COUNT, word.wordCount)
            db.insert(DatabaseHandler.TABLE_WORDS, null, values)
        }
        db.close()
    }

    fun getWords(): DataResponse{
        db = DatabaseHandler(App.context).writableDatabase
        val wordsList = ArrayList<Word>()
            val selectQuery = "SELECT  * FROM ${DatabaseHandler.TABLE_WORDS}"
            val cursor = db.rawQuery(selectQuery, null)

            if (cursor.moveToFirst()) {
                do {
                    val word = Word(
                        cursor.getString(1), cursor.getInt(2)
                    )
                    wordsList.add(word)
                } while (cursor.moveToNext())
            }
            return DataResponse(wordsList)
        }

    fun isTableEmpty(): Boolean{
        db = DatabaseHandler(App.context).writableDatabase
        val count = "SELECT count(*) FROM "+ DatabaseHandler.TABLE_WORDS
        val cursor = db.rawQuery(count, null)
        cursor.moveToFirst()
        val rowCount = cursor.getInt(0)
        return rowCount <= 0
    }
}
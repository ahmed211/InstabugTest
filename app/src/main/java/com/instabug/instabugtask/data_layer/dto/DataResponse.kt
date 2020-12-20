package com.instabug.instabugtask.data_layer.dto

data class DataResponse(val words: List<Word>)

data class Word(var word: String?, var wordCount: Int?)
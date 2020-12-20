package com.instabug.instabugtask

import com.instabug.instabugtask.data_layer.dto.DataResponse
import com.instabug.instabugtask.data_layer.dto.Word
import com.instabug.instabugtask.data_layer.remote.WordsRemoteUseCase
import org.junit.Test

import org.junit.Assert.*

class WordsRemoteUseCaseTest {

    @Test
    fun `mapContent() with html content then return words with number of repeated`() {

        val wordsRemoteUseCase = WordsRemoteUseCase()
        val content = "<HTML><title>Ship Quality Apps with Instabug Instabug Apps Apps with</title></HTML>"
        val wordsList = listOf<Word>(Word("with", 2), Word("Quality", 1),
            Word("Instabug", 2), Word("Ship", 1), Word("Apps", 3))

        val result = wordsRemoteUseCase.mapContent(content)
        val expect = DataResponse(wordsList)
        assertEquals(result, expect)
    }
}
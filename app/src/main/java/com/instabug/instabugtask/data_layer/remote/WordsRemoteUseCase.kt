package com.instabug.instabugtask.data_layer.remote

import com.instabug.instabugtask.data_layer.dto.DataResponse
import com.instabug.instabugtask.data_layer.dto.Word
import java.net.URL
import java.util.ArrayList

class WordsRemoteUseCase {

    fun getWords(): DataResponse{
        val content = URL("https://instabug.com").readText()
        return mapContent(content)
    }

    fun mapContent(content: String): DataResponse {
        val wordsMap = HashMap<String, Int>()
        val contentList = content.split(">")

        contentList.filter { s ->
            if (s.isNotEmpty() && (s[0] in 'A'..'Z' || s[0] in 'a'..'z')
                && !s.startsWith("span") && !s.startsWith("body")
            ) {
                var line = s.split("<")[0].split(" ")
                for (word in line) {
                    if (word.isNotEmpty() && (word[0] in 'A'..'Z' || word[0] in 'a'..'z')) {
                        if (wordsMap.keys.contains(word)) {
                            wordsMap[word] = wordsMap[word]!!.plus(1)
                        } else {
                            wordsMap[word] = 1
                        }
                    }
                }
                true
            } else {
                false
            }
        }
        val wordsList = ArrayList<Word>()

        for ((key, value) in wordsMap) {
            wordsList.add(Word(key, value))
        }

        return DataResponse(wordsList)
    }

}
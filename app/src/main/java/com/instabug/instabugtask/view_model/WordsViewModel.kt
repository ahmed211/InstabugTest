package com.instabug.instabugtask.view_model

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.instabug.instabugtask.utils.ResponseResource
import com.instabug.instabugtask.data_layer.dto.DataResponse
import com.instabug.instabugtask.data_layer.reprository.WordsRepo
import java.util.concurrent.Executors

class WordsViewModel: ViewModel() {

    var response =  MutableLiveData<ResponseResource<DataResponse>>()
    private var wordsRepo = WordsRepo()

    fun getWords(){
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        executor.execute {
            val wordsResponse = wordsRepo.getWebsiteWords()
            handler.post {
                response.value = wordsResponse
            }

        }
    }
}
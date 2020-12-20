package com.instabug.instabugtask.data_layer.reprository

import com.instabug.instabugtask.R
import com.instabug.instabugtask.utils.ResponseResource
import com.instabug.instabugtask.data_layer.dto.DataResponse
import com.instabug.instabugtask.data_layer.local.WordsLocalUseCase
import com.instabug.instabugtask.data_layer.remote.WordsRemoteUseCase
import com.instabug.instabugtask.utils.App
import com.instabug.instabugtask.utils.NetworkUtils

open class WordsRepo {

    val wordsRemoteUseCase = WordsRemoteUseCase()
    val wordsLocalUseCase = WordsLocalUseCase()

    fun getWebsiteWords(): ResponseResource<DataResponse>? {

        var data: ResponseResource<DataResponse>? = null
        if (NetworkUtils.isInternetAvailable()) {
            try {
                data = ResponseResource.success(wordsRemoteUseCase.getWords())
                wordsLocalUseCase.addWords(data!!.data!!.words)
            } catch (e: Exception) {
                data = ResponseResource.error(
                    App.context!!.resources.getString(R.string.netWork_error),
                    null, 400
                )
            }

        } else if (!wordsLocalUseCase.isTableEmpty()) {
            data = ResponseResource.success(wordsLocalUseCase.getWords())
        } else {
            data = ResponseResource.error(
                App.context!!.resources.getString(R.string.internet_connection_error),
                null, 500
            )
        }

        return data
    }
}
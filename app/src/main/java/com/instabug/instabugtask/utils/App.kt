package com.instabug.instabugtask.utils

import android.app.Application
import android.util.Log

class App: Application() {
    companion object {
        var context: App? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}
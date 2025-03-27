package com.dacanay_xyzhie_f.dacanay.ladon_app.core.utils

import android.app.Application
import android.content.Context

class App : Application() {
    init {
        instance = this
    }

    companion object {
        private lateinit var instance: App
        val context: Context
            get() = instance.applicationContext
    }
}
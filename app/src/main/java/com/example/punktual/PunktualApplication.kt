package com.example.punktual

import android.app.Application
import android.content.Context

class PunktualApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}
package com.example.punktual

import android.app.Application
import com.example.punktual.helpers.Store

class PunktualApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Store.setContext(applicationContext)
    }
}
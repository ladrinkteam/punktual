package com.example.punktual.helpers

import android.content.Context.MODE_PRIVATE
import com.example.punktual.PunktualApplication

class Store {
    companion object {
        fun putString (key: String ,value: String) {
            val editor = PunktualApplication.appContext.getSharedPreferences("Punkitual", MODE_PRIVATE).edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getString (key: String): String? {
            return PunktualApplication.appContext.getSharedPreferences("Punkitual", MODE_PRIVATE).getString(key, null) // 0 - for private mode
        }
    }
}
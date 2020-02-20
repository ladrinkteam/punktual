package com.example.punktual.helpers

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Store {
    companion object {
        lateinit var storeContext: Context

        fun initStoreContext (storeContext: Context) {
            this.storeContext = storeContext
        }

        fun putString (key: String ,value: String) {
            val editor = storeContext.getSharedPreferences("Punktual", MODE_PRIVATE).edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getString (key: String): String? {
            return storeContext.getSharedPreferences("Punktual", MODE_PRIVATE).getString(key, null) // 0 - for private mode
        }
    }
}
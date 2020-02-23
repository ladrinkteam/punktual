package com.example.punktual.helpers

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Store {
    companion object {
        private lateinit var context: Context

        fun setContext (context: Context) {
            this.context = context
        }

        fun getContext(): Context {
            return context
        }

        fun putString (key: String ,value: String) {
            val editor = context.getSharedPreferences("Punktual", MODE_PRIVATE).edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun getString (key: String): String? {
            return context.getSharedPreferences("Punktual", MODE_PRIVATE).getString(key, null) // 0 - for private mode
        }
    }
}
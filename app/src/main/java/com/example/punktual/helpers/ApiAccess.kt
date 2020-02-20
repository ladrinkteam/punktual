package com.example.punktual.helpers

import android.content.Context
import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class ApiAccess {
    companion object {
        lateinit var storeContext: Context

        private var server: String = "http://d3cima.tech:8080"

        fun getAll() {
            var client = OkHttpClient()
            val request: Request = Request.Builder()
                .url(server + "/all")
                .build()

            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call, response: Response) {
                    Log.i("carl", response.body!!.string())
                }

                override fun onFailure(call: Call, e: java.io.IOException) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
        }

        fun postSign(path: String, username: String, pushToken: String) {
            val payload = """{"username": "$username", "pushToken": "$pushToken"}""".trimIndent()

            Log.i("carl", payload)

            var client = OkHttpClient()
            val JSON = "application/json; charset=utf-8".toMediaType()
            var requestBody = payload.toRequestBody(JSON)

            val myRequest = Request.Builder()
                .post(requestBody)
                .url(server + path)
                .build()

            client.newCall(myRequest).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    // Handle this
                    Log.i("carl", "onResponse")
                    Log.i("carl", response.body!!.string())
                    Log.i("carl", response.code.toString())
                    Log.i("carl", response.message.toString())
                }

                override fun onFailure(call: Call, e: IOException) {
                    // Handle this
                    Log.i("carl", "onFailure")
                }
            })
        }
    }
}
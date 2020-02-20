package com.example.punktual.interfaces

import com.example.punktual.models.User
import com.example.punktual.models.UserToConnect
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface PunktualService {
    @POST("/login")
    fun login(@Body userToConnect: UserToConnect): Call<User>
}
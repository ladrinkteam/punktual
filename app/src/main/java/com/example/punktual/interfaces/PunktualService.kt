package com.example.punktual.interfaces

import com.example.punktual.models.Position
import com.example.punktual.models.User
import com.example.punktual.models.UserToConnect
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PunktualService {
    @POST("/login")
    fun login(@Body userToConnect: UserToConnect): Call<User>

    @POST("/register")
    fun register(@Body userToConnect: UserToConnect): Call<User>

    @POST ("/position/register/{type}")
    fun positionRegister(@Body position: Position, @Path("type") type: String, @Query("userId") id: String?): Call<ResponseBody>
}
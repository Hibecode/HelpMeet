package com.example.helpmeet.api

import com.example.helpmeet.models.Estate
import retrofit2.Response
import retrofit2.http.POST

interface HelpMeetApi {

    @POST("/api/v1/estate/registration")
    suspend fun registerUser(): Response<Estate>
}
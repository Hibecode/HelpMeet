package com.example.helpmeet.api

import com.example.helpmeet.models.Estate
import com.example.helpmeet.models.SavedEstateDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HelpMeetApi {

    @POST("api/v1/estate/registration/")
    suspend fun registerEstate(
        @Body estateRegData: Estate
    ): Response<Estate>

    @POST("api/v1/user/registration/")
    suspend fun registerUser(
        @Body estateRegData: Estate
    ): Response<Estate>

    @GET("/api/v1/estate/all/")
    suspend fun getEstateList(
        @Query("page") page: Int = 31703080
    ) : Response<List<SavedEstateDetails>>


}
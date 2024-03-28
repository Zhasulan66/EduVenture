package com.example.eduventure.data.remote

import retrofit2.http.GET

interface EduVentureApiService {

    @GET("university/")
    suspend fun getAllUniversity()
}
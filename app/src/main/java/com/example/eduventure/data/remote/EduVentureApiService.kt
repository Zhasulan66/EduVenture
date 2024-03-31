package com.example.eduventure.data.remote

import com.example.eduventure.domain.model.*
import com.example.eduventure.domain.model.Auth.UserRequest
import com.example.eduventure.domain.model.Auth.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EduVentureApiService {

    @GET("/api/news/")
    suspend fun getAllNews(): List<News>

    @GET("/api/universities/")
    suspend fun getAllUniversities(): List<University>

    @GET("/api/internships/")
    suspend fun getAllInternships(): List<Internship>

    @POST("/register/")
    suspend fun registerUser(@Body userRequest: UserRequest): UserResponse
}
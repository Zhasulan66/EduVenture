package com.example.eduventure.data.remote

import com.example.eduventure.domain.model.*
import com.example.eduventure.domain.model.Auth.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface EduVentureApiService {

    @POST("/register/")
    suspend fun registerUser(@Body userRequest: UserRequest): UserResponse

    @POST("/verify_email/")
    suspend fun verifyEmail(@Body verificationRequest: VerificationRequest): MessageResponse

    @POST("/confirm_email/")
    suspend fun confirmEmailCode(@Body confirmCodeRequest: ConfirmCodeRequest): MessageResponse

    @POST("/login/")
    suspend fun loginUser(@Body userLogin: UserLogin): TokenResponse

    @POST("/send_reset_code/")
    suspend fun sendResetCode(@Body resetCodeRequest: ResetCodeRequest): ResetCodeResponse

    @POST("/verify_reset_code/")
    suspend fun verifyResetCode(@Body verifyCodeRequest: VerifyCodeRequest): VerifyCodeResponse

    @POST("/reset_password/")
    suspend fun resetPassword(@Body resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse

    @GET("/api/news/")
    suspend fun getAllNews(): List<News>

    @GET("/api/universities/")
    suspend fun getAllUniversities(
        @Query("country") country: String
    ): List<University>

    @GET("/api/internships/")
    suspend fun getAllInternships(
        @Query("profession") profession: Int?
    ): List<Internship>

    @GET("/api/proffessions/")
    suspend fun getAllProfessions(): List<Profession>

    @GET("/api/news/{id}")
    suspend fun getNewsById(@Path("id") id: Int): News

    @GET("/api/universities/{id}")
    suspend fun getUniversityById(@Path("id") id: Int): University

    @GET("/api/internships/{id}")
    suspend fun getInternshipById(@Path("id") id: Int): Internship

    @GET("/users/me")
    suspend fun getUserByToken(
        @Header("Authorization") token: String,
    ): User

    @Multipart
    @PUT("/api/users/{id}/")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Path("id") pathId: Int,
        @Part("id") id: RequestBody,
        @Part("email") email: RequestBody,
        @Part("username") username: RequestBody,
        @Part("phone_num") phoneNum: RequestBody?,
        @Part photo: MultipartBody.Part?
    ): Call<User>




}
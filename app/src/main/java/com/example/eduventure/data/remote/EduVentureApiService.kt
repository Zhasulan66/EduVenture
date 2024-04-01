package com.example.eduventure.data.remote

import com.example.eduventure.domain.model.*
import com.example.eduventure.domain.model.Auth.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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
    suspend fun getAllUniversities(): List<University>

    @GET("/api/internships/")
    suspend fun getAllInternships(): List<Internship>

    @GET("/api/news/{id}")
    suspend fun getNewsById(@Path("id") id: Int): News


}
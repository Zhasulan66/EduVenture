package com.example.eduventure.domain.repository

import com.example.eduventure.domain.model.*
import com.example.eduventure.domain.model.Auth.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

interface EduVentureRepository {

    suspend fun registerUser(userRequest: UserRequest): UserResponse

    suspend fun verifyEmail(verificationRequest: VerificationRequest): MessageResponse

    suspend fun confirmEmailCode(confirmCodeRequest: ConfirmCodeRequest): MessageResponse

    suspend fun loginUser(userLogin: UserLogin): TokenResponse

    suspend fun sendResetCode(resetCodeRequest: ResetCodeRequest): ResetCodeResponse

    suspend fun verifyResetCode(verifyCodeRequest: VerifyCodeRequest): VerifyCodeResponse

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse

    suspend fun getAllNews(): List<News>

    suspend fun getAllUniversities(country: String): List<University>

    suspend fun getAllInternships(profession: Int?): List<Internship>
    suspend fun getAllProfessions(): List<Profession>

    suspend fun getNewsById(id: Int): News

    suspend fun getUniversityById(id: Int): University

    suspend fun getInternshipById(id: Int): Internship

    suspend fun getUserByToken(token: String): User

    suspend fun updateUser(
        token: String,
        pathId: Int,
        id: RequestBody,
        email: RequestBody,
        username: RequestBody,
        phoneNum: RequestBody?,
        photo: MultipartBody.Part?
    ): Call<User>

}
package com.example.eduventure.domain.repository

import com.example.eduventure.domain.model.*
import com.example.eduventure.domain.model.Auth.*

interface EduVentureRepository {

    suspend fun registerUser(userRequest: UserRequest): UserResponse

    suspend fun verifyEmail(verificationRequest: VerificationRequest): MessageResponse

    suspend fun confirmEmailCode(confirmCodeRequest: ConfirmCodeRequest): MessageResponse

    suspend fun loginUser(userLogin: UserLogin): TokenResponse

    suspend fun sendResetCode(resetCodeRequest: ResetCodeRequest): ResetCodeResponse

    suspend fun verifyResetCode(verifyCodeRequest: VerifyCodeRequest): VerifyCodeResponse

    suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse

    suspend fun getAllNews(): List<News>

    suspend fun getAllUniversities(): List<University>

    suspend fun getAllInternships(): List<Internship>

    suspend fun getNewsById(id: Int): News

}
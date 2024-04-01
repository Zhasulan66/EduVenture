package com.example.eduventure.data.repository

import com.example.eduventure.data.remote.EduVentureApiService
import com.example.eduventure.domain.model.Auth.UserRequest
import com.example.eduventure.domain.model.Auth.UserResponse
import com.example.eduventure.domain.model.Internship
import com.example.eduventure.domain.model.News
import com.example.eduventure.domain.model.University
import com.example.eduventure.domain.model.Auth.ConfirmCodeRequest
import com.example.eduventure.domain.model.Auth.MessageResponse
import com.example.eduventure.domain.model.Auth.ResetCodeRequest
import com.example.eduventure.domain.model.Auth.ResetCodeResponse
import com.example.eduventure.domain.model.Auth.ResetPasswordRequest
import com.example.eduventure.domain.model.Auth.ResetPasswordResponse
import com.example.eduventure.domain.model.Auth.TokenResponse
import com.example.eduventure.domain.model.Auth.UserLogin
import com.example.eduventure.domain.model.Auth.VerificationRequest
import com.example.eduventure.domain.model.Auth.VerifyCodeRequest
import com.example.eduventure.domain.model.Auth.VerifyCodeResponse
import com.example.eduventure.domain.repository.EduVentureRepository

class EduVentureRepositoryImpl(
    private val api: EduVentureApiService
) : EduVentureRepository {

    override suspend fun getAllNews(): List<News> {
        return api.getAllNews()
    }

    override suspend fun getAllUniversities(): List<University> {
        return api.getAllUniversities()
    }

    override suspend fun getAllInternships(): List<Internship> {
        return api.getAllInternships()
    }

    override suspend fun registerUser(userRequest: UserRequest): UserResponse {
        return api.registerUser(userRequest)
    }

    override suspend fun verifyEmail(verificationRequest: VerificationRequest): MessageResponse {
        return api.verifyEmail(verificationRequest)
    }

    override suspend fun confirmEmailCode(confirmCodeRequest: ConfirmCodeRequest): MessageResponse {
        return api.confirmEmailCode(confirmCodeRequest)
    }

    override suspend fun loginUser(userLogin: UserLogin): TokenResponse {
        return api.loginUser(userLogin)
    }

    override suspend fun sendResetCode(resetCodeRequest: ResetCodeRequest): ResetCodeResponse {
        return api.sendResetCode(resetCodeRequest)
    }

    override suspend fun verifyResetCode(verifyCodeRequest: VerifyCodeRequest): VerifyCodeResponse {
        return api.verifyResetCode(verifyCodeRequest)
    }

    override suspend fun resetPassword(resetPasswordRequest: ResetPasswordRequest): ResetPasswordResponse {
        return api.resetPassword(resetPasswordRequest)
    }

    override suspend fun getNewsById(id: Int): News {
        return api.getNewsById(id)
    }
}
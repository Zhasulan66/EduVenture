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
import com.example.eduventure.domain.model.Profession
import com.example.eduventure.domain.model.User
import com.example.eduventure.domain.repository.EduVentureRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

class EduVentureRepositoryImpl(
    private val api: EduVentureApiService
) : EduVentureRepository {

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

    override suspend fun getAllNews(): List<News> {
        return api.getAllNews()
    }

    override suspend fun getAllUniversities(country: String): List<University> {
        return api.getAllUniversities(country)
    }

    override suspend fun getAllInternships(profession: Int?): List<Internship> {
        return api.getAllInternships(profession)
    }

    override suspend fun getAllProfessions(): List<Profession> {
        return api.getAllProfessions()
    }

    override suspend fun getNewsById(id: Int): News {
        return api.getNewsById(id)
    }

    override suspend fun getUniversityById(id: Int): University {
        return api.getUniversityById(id)
    }

    override suspend fun getInternshipById(id: Int): Internship {
        return api.getInternshipById(id)
    }

    override suspend fun getUserByToken(token: String): User {
        return api.getUserByToken(token)
    }

    override suspend fun updateUser(
        token: String,
        pathId: Int,
        id: RequestBody,
        email: RequestBody,
        username: RequestBody,
        phoneNum: RequestBody?,
        photo: MultipartBody.Part?
    ): Call<User> {
        return api.updateUser(token, pathId, id, email, username, phoneNum, photo)
    }
}
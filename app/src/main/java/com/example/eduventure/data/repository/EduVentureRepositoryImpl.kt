package com.example.eduventure.data.repository

import com.example.eduventure.data.remote.EduVentureApiService
import com.example.eduventure.domain.model.Auth.UserRequest
import com.example.eduventure.domain.model.Auth.UserResponse
import com.example.eduventure.domain.model.Internship
import com.example.eduventure.domain.model.News
import com.example.eduventure.domain.model.University
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
}
package com.example.eduventure.domain.repository

import com.example.eduventure.domain.model.*
import com.example.eduventure.domain.model.Auth.*

interface EduVentureRepository {

    suspend fun getAllNews(): List<News>
    suspend fun getAllUniversities(): List<University>
    suspend fun getAllInternships(): List<Internship>

    suspend fun registerUser(userRequest: UserRequest): UserResponse

}